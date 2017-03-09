import {Injectable} from '@angular/core';
import {Http, Response, RequestOptions} from '@angular/http';
import {Project} from './project.model';
import 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {HttpHeadersService} from '../../core/http-headers.service';

@Injectable()
export class ProjectService {

    options = new RequestOptions({headers: this.httpHeadersService.getHeaders()});

    constructor(private http: Http, private httpHeadersService: HttpHeadersService) {
    }

    getAll(): Observable<Project[]> {
        return this.http.get('/api/projects', this.options)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body._embedded.projects || {};
    }

    private handleError(error: any) {
        // In a real world app, we might use a remote logging infrastructure
        // We"d also dig deeper into the error to get a better message
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}
