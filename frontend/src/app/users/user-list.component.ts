import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../shared/model/user-resource.model';
import {UserService} from '../shared/service/user-rest.service';

@Component({
    moduleId: module.id,
    selector: 'user-list',
    templateUrl: 'user-list.component.html',
    styleUrls: ['user-list.component.css'],
    providers: [UserService]
})
export class UserListComponent implements OnInit {
    users: User[] = [];

    constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {
    }

    ngOnInit() {
        this.userService.query().subscribe(users => this.users = users);
    }

    edit(user: User) {
        this.router.navigate([user.login], {relativeTo: this.route});
    }

    delete(user: User) {
        this.router.navigate([user.login], {relativeTo: this.route});
    }
}
