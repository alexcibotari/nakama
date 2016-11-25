import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {Project} from "./project.model";
import "rxjs/Observable";
import 'rxjs/add/observable/throw';
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';

@Injectable()
export class ProjectService {

    headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json;charset=UTF-8' });
    options = new RequestOptions({ headers: this.headers });

    constructor(private http: Http) {
    }

    getAll(): Observable<Project[]> {
        return this.http.get('/api/projects', this.options)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body._embedded.projects || { };
    }

    private handleError (error: any) {
        // In a real world app, we might use a remote logging infrastructure
        // We'd also dig deeper into the error to get a better message
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}
