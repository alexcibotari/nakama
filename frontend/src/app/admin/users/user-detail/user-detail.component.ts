import {Component, Inject, OnInit} from '@angular/core';
import {UserResource} from "../../../shared/model/user-resource.model";
import {MD_DIALOG_DATA} from "@angular/material";
import {UserService} from "../../shared/user-rest.service";
import {ObjectUtils} from "../../../core/object-utils.service";

@Component({
    moduleId: module.id,
    selector: 'nkm-user-detail',
    templateUrl: 'user-detail.component.html',
    styleUrls: ['user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
    public model: UserResource;

    constructor(@Inject(MD_DIALOG_DATA) public data: UserResource, private userService: UserService, private objectUtils: ObjectUtils) {

    }

    ngOnInit() {
        this.model = this.objectUtils.clone(this.data);
    }

}
