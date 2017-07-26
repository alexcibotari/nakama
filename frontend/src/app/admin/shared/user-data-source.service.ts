import {CollectionViewer, DataSource} from "@angular/cdk";
import {Observable} from "rxjs/Observable";
import {UserService} from "./user-rest.service";
import {Resources} from "../../core/web/http/hal/hal.model";
import {Injectable} from "@angular/core";
import {UserResource} from "../../shared/model/user-resource.model";

@Injectable()
export class UserDataSource extends DataSource<UserResource> {

    constructor(private userService: UserService) {
        super();
    }

    connect(collectionViewer: CollectionViewer): Observable<UserResource[]> {
        return this.userService.query().map((res: Resources<UserResource>) => {
            return res._embedded.users
        });
    }

    disconnect(collectionViewer: CollectionViewer): void {
    }

}
