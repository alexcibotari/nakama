import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';
import {ObjectUtils} from '../../../core/object-utils.service';
import {User} from '../../shared/user.model';

@Component({
    moduleId: module.id,
    selector: 'nkm-user-edit',
    templateUrl: 'user-edit.component.html',
    styleUrls: ['user-edit.component.scss']
})
export class UserEditComponent implements OnInit {
    public model: User;

    constructor(@Inject(MAT_DIALOG_DATA) public data: User, private objectUtils: ObjectUtils) {
    }

    ngOnInit() {
        this.model = this.objectUtils.clone(this.data);
        console.log(this.model);
    }

}
