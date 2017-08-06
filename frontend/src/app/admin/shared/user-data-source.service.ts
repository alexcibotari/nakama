import {CollectionViewer, DataSource} from "@angular/cdk";
import {Observable} from "rxjs/Observable";
import {UserService} from "./user-rest.service";
import {Resources} from "../../core/web/http/hal/hal.model";
import {Injectable} from "@angular/core";
import {UserResource} from "../../shared/model/user-resource.model";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import 'rxjs/add/observable/merge';

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

    constructor(private userService: UserService) {
        super();
        this.userService.query().subscribe((res: Resources<UserResource>) => {
            this._dataChanges.next(res._embedded.users);
        });
    }

    connect(collectionViewer: CollectionViewer): Observable<UserResource[]> {
        return Observable.merge(...[this._dataChanges, this._filterChange]).map(() => {
            return this._dataChanges.getValue().slice().filter((item: UserResource) => {
                return (item.login + item.email + item.personal.fullName).toLowerCase().indexOf(this._filterChange.getValue().toLowerCase()) != -1;
            });
        });
    }

    disconnect(collectionViewer: CollectionViewer): void {
    }

}
