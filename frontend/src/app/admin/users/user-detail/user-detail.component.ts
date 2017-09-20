import {Component, Inject, OnInit} from '@angular/core';
import {UserResource} from '../../../shared/model/user-resource.model';
import {MD_DIALOG_DATA, MdDialog} from '@angular/material';
import {UserEditComponent} from '../user-edit/user-edit.component';

@Component({
    moduleId: module.id,
    selector: 'nkm-user-detail',
    templateUrl: 'user-detail.component.html',
    styleUrls: ['user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
    public model: UserResource;

    constructor(@Inject(MD_DIALOG_DATA) public data: UserResource, private dialog: MdDialog) {
        this.model = this.data;
    }

    ngOnInit() {
    }

    toEdit(user: UserResource) {
        let dialogRef = this.dialog.open(UserEditComponent, {data: user});
        dialogRef.afterClosed().subscribe((result: UserResource) => {
            console.log(result);
        });
    }

}
