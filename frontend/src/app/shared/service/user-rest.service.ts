import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {RESTService} from '../../core/http/http-rest.service';
import {UserResource} from '../model/user-resource.model';
import {OAuthService} from './oauth.service';

@Injectable()
export class UserService extends RESTService<UserResource> {

    constructor(http: Http, router: Router, oAuthService : OAuthService) {
        super(http,
            {
                baseUrl: environment.api.url,
                path: '/users'
            },
            router,
            oAuthService
        );
    }

    public getCurrent(): Observable<UserResource> {
        return this.http.get(environment.api.url + '/me', this.buildRequestOptions()).map(res => res.json());
    }
}
