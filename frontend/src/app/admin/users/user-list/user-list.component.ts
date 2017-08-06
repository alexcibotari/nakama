import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserResource} from '../../../shared/model/user-resource.model';
import {MdDialog} from "@angular/material";
import {ConfirmationDialogComponent} from "../../../shared/component/dialog/confirmation-dialog/confirmation-dialog.component";
import {UserDetailComponent} from "../user-detail/user-detail.component";
import {UserService} from "../../shared/user-rest.service";
import {UserDataSource} from "../../shared/user-data-source.service";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/observable/fromEvent';

@Component({
    moduleId: module.id,
    selector: 'nkm-user-list',
    templateUrl: 'user-list.component.html',
    styleUrls: ['user-list.component.scss'],
    providers: [UserService]
})
export class UserListComponent implements OnInit {
    displayedColumns = ['avatar', 'fullName', 'login', 'email', 'actions'];

    @ViewChild('filter') filter: ElementRef;

    constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, public dataSource: UserDataSource, private dialog: MdDialog) {
    }

    ngOnInit() {
        Observable.fromEvent(this.filter.nativeElement, 'keyup')
            .debounceTime(150)
            .distinctUntilChanged()
            .subscribe(() => {
                this.dataSource.filter = this.filter.nativeElement.value;
            });
    }

    toDetail(user: UserResource) {
        let dialogRef = this.dialog.open(UserDetailComponent, {data: user});
    }

    toEdit(user: UserResource) {
        let dialogRef = this.dialog.open(UserDetailComponent, {data: user});
    }

    toDelete(user: UserResource) {
        let dialogRef = this.dialog.open(ConfirmationDialogComponent, {data: {content: `Delete ${user.personal.fullName} user ?`}});
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
