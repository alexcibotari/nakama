import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../shared/service/user-rest.service';

@Component({
    moduleId: module.id,
    selector: 'user-detail',
    templateUrl: 'user-detail.component.html',
    styleUrls: ['user-detail.component.css'],
    providers: [UserService]
})
export class UserDetailComponent implements OnInit {
    public model: any = {};

    constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {
    }

    ngOnInit() {
        this.route.params.subscribe(params => {
            let userId: string = params['id'];
            if (userId) {
                this.userService.get(userId).subscribe(user => this.model = user);
            }
        });
    }

    onSubmit() {

    }
}
