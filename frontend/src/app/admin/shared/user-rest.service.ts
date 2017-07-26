import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';
import {RESTService} from "../../core/web/http/http-rest.service";
import {UserResource} from "../../shared/model/user-resource.model";
import {AuthService} from "../../shared/auth/auth.service";

@Injectable()
export class UserService extends RESTService<UserResource> {

    constructor(http: Http, router: Router, authService: AuthService) {
        super(http,
            {
                baseUrl: environment.api.url,
                path: '/users'
            },
            router,
            authService
        );
    }
}
