import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../../shared/service/user-rest.service';
import {UserResource} from "../../../shared/model/user-resource.model";
import {MD_DIALOG_DATA} from "@angular/material";

@Component({
    moduleId: module.id,
    selector: 'user-detail',
    templateUrl: 'user-detail.component.html',
    styleUrls: ['user-detail.component.css'],
    providers: [UserService]
})
export class UserDetailComponent implements OnInit {
    public model: UserResource;

    constructor(@Inject(MD_DIALOG_DATA) private data: UserResource, private userService: UserService) {
        this.model = data;
    }

    ngOnInit() {
    }

}
