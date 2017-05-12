import {Headers, Request, RequestOptionsArgs, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {Subscriber} from 'rxjs/Subscriber';
import {Resource} from './hal/resource.model';
import {Resources} from './hal/resources.model';

export interface RestTransform {
    (response: Response): any;
}

export interface RestConfig {
    baseHeaders?: Headers;
    dynamicHeaders?: () => Headers;
    baseUrl: string;
    path?: string;
    transform?: RestTransform;
}

export interface RestQuery {
    [key: string]: any;
}

export interface Http {
    delete: (url: string, options?: RequestOptionsArgs) => Observable<Response>;
    get: (url: string, options?: RequestOptionsArgs) => Observable<Response>;
    head: (url: string, options?: RequestOptionsArgs) => Observable<Response>;
    patch: (url: string, body: any, options?: RequestOptionsArgs) => Observable<Response>;
    post: (url: string, body: any, options?: RequestOptionsArgs) => Observable<Response>;
    put: (url: string, body: any, options?: RequestOptionsArgs) => Observable<Response>;
    request: (url: string | Request, options?: RequestOptionsArgs) => Observable<Response>;
}

export abstract class RESTService<T extends Resource> {

    constructor(protected http: Http, private config: RestConfig) {
        this.config.baseUrl = config.baseUrl.replace(/\/$/, '');
        this.config.path = config.path.replace(/^\//, '');
        this.config.baseHeaders = config.baseHeaders ? this.config.baseHeaders : new Headers();
        this.config.dynamicHeaders = config.dynamicHeaders ? this.config.dynamicHeaders : () => new Headers();
        this.config.transform = config.transform ? this.config.transform : (response: Response): any => response.json();
    }

    public query(query?: RestQuery, transform?: RestTransform): Observable<Resources<T>> {
        let request: Observable<Response> = this.http.get(this.buildUrl(undefined, query), this.buildRequestOptions());
        return request.map((res: Response) => {
            if (transform) {
                return transform(res);
            }
            return this.config.transform(res);
        }).catch((error: Response) => {
            return new Observable<any>((subscriber: Subscriber<any>) => {
                try {
                    subscriber.error(this.config.transform(error));
                } catch (err) {
                    subscriber.error(error);
                }
            });
        });
    }

    public get(id: string | number, transform?: RestTransform): Observable<T> {
        let request: Observable<Response> = this.http.get(this.buildUrl(id), this.buildRequestOptions());
        return request.map((res: Response) => {
            if (transform) {
                return transform(res);
            }
            return this.config.transform(res);
        }).catch((error: Response) => {
            return new Observable<any>((subscriber: Subscriber<any>) => {
                try {
                    subscriber.error(this.config.transform(error));
                } catch (err) {
                    subscriber.error(error);
                }
            });
        });
    }

    public create(obj: T, transform?: RestTransform): Observable<T> {
        let requestOptions: RequestOptionsArgs = this.buildRequestOptions();
        let request: Observable<Response> = this.http.post(this.buildUrl(), obj, requestOptions);
        return request.map((res: Response) => {
            if (res.status === 201) {
                if (transform) {
                    return transform(res);
                }
                return this.config.transform(res);
            } else {
                return res;
            }
        }).catch((error: Response) => {
            return new Observable<any>((subscriber: Subscriber<any>) => {
                try {
                    subscriber.error(this.config.transform(error));
                } catch (err) {
                    subscriber.error(error);
                }
            });
        });
    }

    public update(id: string | number, obj: T, transform?: RestTransform): Observable<T> {
        let requestOptions: RequestOptionsArgs = this.buildRequestOptions();
        let request: Observable<Response> = this.http.patch(this.buildUrl(id), obj, requestOptions);
        return request.map((res: Response) => {
            if (res.status === 200) {
                if (transform) {
                    return transform(res);
                }
                return this.config.transform(res);
            } else {
                return res;
            }
        }).catch((error: Response) => {
            return new Observable<any>((subscriber: Subscriber<any>) => {
                try {
                    subscriber.error(this.config.transform(error));
                } catch (err) {
                    subscriber.error(error);
                }
            });
        });
    }

    public delete(id: string | number, transform?: RestTransform): Observable<T> {
        let request: Observable<Response> = this.http.delete(this.buildUrl(id), this.buildRequestOptions());
        return request.map((res: Response) => {
            if (res.status === 200) {
                if (transform) {
                    return transform(res);
                }
                return this.config.transform(res);
            } else {
                return res;
            }
        }).catch((error: Response) => {
            return new Observable<any>((subscriber: Subscriber<any>) => {
                try {
                    subscriber.error(this.config.transform(error));
                } catch (err) {
                    subscriber.error(error);
                }
            });
        });
    }

    protected buildRequestOptions(): RequestOptionsArgs {
        let requestHeaders: Headers = new Headers();
        this.config.baseHeaders.forEach((value: string[], key: string) => {
            requestHeaders.set(key, value);
        });
        this.config.dynamicHeaders().forEach((value: string[], key: string) => {
            requestHeaders.set(key, value);
        });
        let requestOptions: RequestOptionsArgs = {
            headers: requestHeaders,
        };
        return requestOptions;
    }

    protected buildUrl(id?: string | number, query?: RestQuery): string {
        let url: string = this.config.path ? this.config.path : '';
        if (id) {
            url += `/${id}`;
        }
        if (query) {
            url += this.buildQuery(query);
        }
        url = `${this.config.baseUrl}/${url}`;
        return url;
    }

    protected buildQuery(query: RestQuery): string {
        let url: string = '';
        if (query) {
            url += '?';
            let params: string[] = [];
            for (let key in query) {
                let value: string | number | boolean = query[key];
                if (value !== undefined) {
                    params.push(`${key}=${value}`);
                }
            }
            url += params.join('&');
        }
        return url;
    }
}
