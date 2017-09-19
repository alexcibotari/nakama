import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserResource} from '../../../shared/model/user-resource.model';
import {MdDialog} from "@angular/material";
import {ConfirmationDialogComponent} from "../../../shared/component/dialog/confirmation-dialog/confirmation-dialog.component";
import {UserDetailComponent} from "../user-detail/user-detail.component";
import {UserRESTService} from "../../shared/user-rest.service";
import {UserDataSource} from "../../shared/user-data-source.service";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/observable/fromEvent';
import {UserEditComponent} from "../user-edit/user-edit.component";
import {ApolloService} from "../../../core/apollo.service";

@Component({
    moduleId: module.id,
    selector: 'nkm-user-list',
    templateUrl: 'user-list.component.html',
    styleUrls: ['user-list.component.scss'],
    providers: [UserRESTService]
})
export class UserListComponent implements OnInit {
    displayedColumns = ['avatar', 'fullName', 'login', 'email', 'actions'];

    @ViewChild('filter') filter: ElementRef;

    constructor(private userService: UserRESTService, public dataSource: UserDataSource, private apollo: ApolloService, private dialog: MdDialog) {
    }

    ngOnInit() {
        Observable.fromEvent(this.filter.nativeElement, 'keyup')
            .debounceTime(150)
            .distinctUntilChanged()
            .subscribe(() => {
                this.dataSource.filter = this.filter.nativeElement.value;
            });

     /*   this.apollo.watchQuery<any>({
            query: users
        }).subscribe((data) => {
            console.log(data);
        });*/
    }

    toDetail(user: UserResource) {
        let dialogRef = this.dialog.open(UserDetailComponent, {data: user});
        dialogRef.afterClosed().subscribe((result: UserResource) => {
            console.log(result);
        });
    }

    toEdit(user: UserResource) {
        let dialogRef = this.dialog.open(UserEditComponent, {data: user});
        dialogRef.afterClosed().subscribe((result: UserResource) => {
            console.log(result);
        });
    }

    toDelete(user: UserResource) {
        let dialogRef = this.dialog.open(ConfirmationDialogComponent, {data: {content: `Delete ${user.personal} user ?`}});
        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.userService
                    .delete(user.login)
                    .subscribe(result => {
                        console.log(result);
                    });
            }
        });
    }
}
