import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material';
import {UserEditComponent} from '../user-edit/user-edit.component';
import {User} from '../../shared/user.model';

@Component({
    moduleId: module.id,
    selector: 'nkm-user-detail',
    templateUrl: 'user-detail.component.html',
    styleUrls: ['user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
    public model: User;

    constructor(@Inject(MAT_DIALOG_DATA) public data: User, private dialog: MatDialog) {
        this.model = this.data;
    }

    ngOnInit() {
    }

    toEdit(user: User) {
        const dialogRef = this.dialog.open(UserEditComponent, {data: user});
        dialogRef.afterClosed().subscribe((result: User) => {
            console.log(result);
        });
    }

}
