import {Injectable} from '@angular/core';
import {RequestOptions, Http} from '@angular/http';
import {HttpHeadersService} from '../../core/http-headers.service';
import {User} from '../../shared/model/user.model';
import {Observable} from 'rxjs';

@Injectable()
export class UserService {

    private _url = '/api/users'
    private _options = new RequestOptions({headers: this.httpHeadersService.getHeaders()});

    constructor(private http: Http, private httpHeadersService: HttpHeadersService) {
    }

    public getUsers(): Observable<Array<User>> {
        return this.http.get(this._url, this._options).map(res => res.json()._embedded.users);
    }

    public getUser(login: string): Observable<User> {
        return this.http.get(this._url, this._options).map(res => res.json());
    }

    public getMe(): Observable<User> {
        return this.http.get('/api/me', this._options).map(res => res.json());
    }
}
