import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';
import {RESTService, RestTransform} from "../../core/web/http/http-rest.service";
import {UserResource} from "../../shared/model/user-resource.model";
import {AuthService} from "../../shared/auth/auth.service";
import {Resources} from "../../core/web/http/hal/hal.model";
import {Observable} from "rxjs/Observable";
import {AuthorityResource} from "../../shared/model/authority-resource.model";

@Injectable()
export class UserRESTService extends RESTService<UserResource> {

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

    public authorities(id: string | number, transform?: RestTransform): Observable<Resources<AuthorityResource>> {
        let response: Observable<Response> = this.http.get(this.buildUrl(id, 'authorities'), this.buildRequestOptions());
        return response.map((res: Response) => {
            if (transform) {
                return transform(res);
            }
            return this.config.transform(res);
        }).catch((error: Response) => {
            return this.errorInterceptor(error);
        });
    }
}
