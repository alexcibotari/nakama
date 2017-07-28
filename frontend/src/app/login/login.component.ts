import {Component, OnInit} from '@angular/core';
import {LoginModel} from './login.model';
import {Router} from '@angular/router';
import {AuthService} from "../shared/auth/auth.service";

@Component({
    selector: 'nkm-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    model: LoginModel = new LoginModel('admin', 'admin');

    constructor(private router: Router, private authService: AuthService) {
    }

    ngOnInit() {
    }

    login() {
        this.authService.login(this.model.login, this.model.password).subscribe();
    }

}
