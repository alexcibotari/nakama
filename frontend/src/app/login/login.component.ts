import {Component, OnInit} from '@angular/core';
import {OAuthService} from '../shared/service/oauth.service';
import {LoginModel} from './login.model';
import {Router} from '@angular/router';

@Component({
    selector: 'login-component',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    model: LoginModel = new LoginModel('admin', 'admin');

    constructor(private router: Router, private oAuthService: OAuthService) {
    }

    ngOnInit() {
    }

    login() {
        this.oAuthService.login(this.model.login, this.model.password).subscribe();
    }

}
