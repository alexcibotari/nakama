import {Injectable} from '@angular/core';
import {Headers, RequestOptions, Http} from '@angular/http';

@Injectable()
export class UserService {

    headers = new Headers({'Accept': 'application/json', 'Content-Type': 'application/json;charset=UTF-8'});
    options = new RequestOptions({headers: this.headers});

    constructor(private http: Http) {
    }

    getUsers() {
        return this.http.get('/api/users', this.options).map(res => res.json()._embedded.users);
    }

    getMe(){
        return this.http.get('/api/me', this.options).map(res => res.json());
    }
}
