import {Component, OnInit} from '@angular/core';
import {User} from './shared/user.model';
import {UserService} from './shared/user.service';
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
