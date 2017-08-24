import {Component, Inject, OnInit} from '@angular/core';
import {UserResource} from "../../../shared/model/user-resource.model";
import {MD_DIALOG_DATA} from "@angular/material";
import {ObjectUtils} from "../../../core/object-utils.service";

@Component({
    moduleId: module.id,
    selector: 'nkm-user-edit',
    templateUrl: 'user-edit.component.html',
    styleUrls: ['user-edit.component.scss']
})
export class UserEditComponent implements OnInit {
    public model: UserResource;

    constructor(@Inject(MD_DIALOG_DATA) public data: UserResource, private objectUtils: ObjectUtils) {

    }

    ngOnInit() {
        this.model = this.objectUtils.clone(this.data);
    }

}
