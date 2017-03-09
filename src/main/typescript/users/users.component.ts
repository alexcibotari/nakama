import {Component, OnInit} from '@angular/core';
import {UserService} from './shared/user.service';
import {User} from '../shared/model/user.model';

@Component({
    moduleId: module.id,
    templateUrl: 'users.component.html',
    providers: [UserService]
})
export class UsersComponent implements OnInit {
    users: User[] = [];

    constructor(private userService: UserService) {
    }

    ngOnInit() {
        this.userService.getUsers().subscribe(users => this.users = users);
        this.userService.getMe().subscribe(me => console.log(me));
    }
}
