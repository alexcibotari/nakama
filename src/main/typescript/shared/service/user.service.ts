import {Injectable} from '@angular/core';
import {Http, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs';
import {HttpHeadersService} from '../../core/http-headers.service';
import {User} from '../model/user.model';

@Injectable()
export class UserService {

    private _url = '/api/users'
    private _options = new RequestOptions({headers: this.httpHeadersService.getHeaders()});

    constructor(private http: Http, private httpHeadersService: HttpHeadersService) {
    }

    public findAll(): Observable<Array<User>> {
        return this.http.get(this._url, this._options).map(res => res.json()._embedded.users);
    }

    public findOneById(id: string): Observable<User> {
        return this.http.get(this._url + '/' + id, this._options).map(res => res.json());
    }

    public current(): Observable<User> {
        return this.http.get('/api/me', this._options).map(res => res.json());
    }
}
