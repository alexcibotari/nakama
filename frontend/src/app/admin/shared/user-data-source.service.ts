import {Observable} from "rxjs/Observable";
import {UserRESTService} from "./user-rest.service";
import {Injectable} from "@angular/core";
import {UserResource} from "../../shared/model/user-resource.model";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import 'rxjs/add/observable/merge';
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import gql from "graphql-tag";
import {ApolloService} from "../../core/apollo.service";

const users = gql`
    query users {
        users {
            id
            login
            email
            personal {
                givenName
                familyName
                birthday
            }
        }
    }`;

interface QueryResponse {
    users: UserResource[]
}

@Injectable()
export class UserDataSource extends DataSource<UserResource> {

    private _dataChanges: BehaviorSubject<UserResource[]> = new BehaviorSubject<UserResource[]>([]);

    _filterChange = new BehaviorSubject('');

    get filter(): string {
        return this._filterChange.value;
    }

    set filter(filter: string) {
        this._filterChange.next(filter);
    }

    constructor(private userService: UserRESTService, private apollo: ApolloService) {
        super();
    }

    connect(collectionViewer: CollectionViewer): Observable<UserResource[]> {
        /*this.userService.query().subscribe((res: Resources<UserResource>) => {
            this._dataChanges.next(res._embedded.users);
        });*/
        this.apollo.watchQuery<QueryResponse>({
            query: users
        }).subscribe((data) => {
            this._dataChanges.next(data.data.users);
            console.log(data);
        });
        return Observable.merge(...[this._dataChanges, this._filterChange]).map(() => {
            return this._dataChanges.getValue().slice().filter((item: UserResource) => {
                return (item.login + item.email + item.personal.givenName + item.personal.familyName).toLowerCase().indexOf(this._filterChange.getValue().toLowerCase()) != -1;
            });
        });
    }

    disconnect(collectionViewer: CollectionViewer): void {
    }

}
