import {Component, OnInit} from '@angular/core';
import {OAuthService} from '../shared/service/oauth.service';
import {LoginModel} from './login.model';
import {Router} from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    user: LoginModel = new LoginModel('admin', 'admin');

    constructor(private router: Router, private oAuthService: OAuthService) {
    }

    ngOnInit() {
        this.oAuthService.logout();
    }

    login() {
        this.oAuthService.login(this.user.login, this.user.password)
            .subscribe(result => {
                if(result){
                    this.router.navigate(['/']);
                }else {
                }
            });
    }

}
