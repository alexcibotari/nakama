import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../shared/service/user-rest.service';
import {UserResource} from '../../../shared/model/user-resource.model';
import {MdDialog} from "@angular/material";
import {ConfirmationDialogComponent} from "../../../shared/component/dialog/confirmation-dialog/confirmation-dialog.component";
import {UserDetailComponent} from "../user-detail/user-detail.component";
import {UserDataSource} from "../../../shared/service/user-data-source.service";

@Component({
    moduleId: module.id,
    selector: 'user-list',
    templateUrl: 'user-list.component.html',
    styleUrls: ['user-list.component.css'],
    providers: [UserService]
})
export class UserListComponent implements OnInit {
    displayedColumns = ['avatar', 'fullName', 'login', 'email', 'actions'];

    constructor(private route: ActivatedRoute, private router: Router, private userService: UserService, public dataSource: UserDataSource, private dialog: MdDialog) {
    }

    ngOnInit() {
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
