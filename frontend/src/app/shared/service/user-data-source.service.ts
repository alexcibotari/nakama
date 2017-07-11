import {CollectionViewer, DataSource} from "@angular/cdk";
import {UserResource} from "../model/user-resource.model";
import {Observable} from "rxjs/Observable";
import {UserService} from "./user-rest.service";
import {Resources} from "../../core/web/http/hal/hal.model";

export class UserDataSource extends DataSource<UserResource> {

    constructor(private userService: UserService){
        super();
    }

    connect(collectionViewer: CollectionViewer): Observable<UserResource[]> {
        return this.userService.query().map((res:Resources<UserResource>) => { return res._embedded.users});
    }

    disconnect(collectionViewer: CollectionViewer): void {
        throw new Error("Method not implemented.");
    }

}
