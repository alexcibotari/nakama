import {Injectable} from '@angular/core';
import {Http, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {HttpHeadersService} from '../../core/http-headers.service';
import {RESTService} from '../../core/http/http-rest.service';
import {UserResource} from '../model/user-resource.model';

@Injectable()
export class UserService extends RESTService<UserResource> {

    private static readonly BASE_URL = environment.apiBaseUrl + '/users';
    private _options = new RequestOptions({headers: this.httpHeadersService.getHeaders()});

    constructor(http: Http, private httpHeadersService: HttpHeadersService) {
        super(http, {baseUrl: UserService.BASE_URL});
    }

    public getCurrent(): Observable<UserResource> {
        return this.http.get(environment.apiBaseUrl + '/api/me', this._options).map(res => res.json());
    }
}
