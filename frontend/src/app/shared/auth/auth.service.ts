import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptionsArgs, Response} from "@angular/http";
import {Router} from "@angular/router";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";
import {environment} from "../../../environments/environment";
import {URLParams} from "../../core/web/http/http-rest.service";

interface OAuthTokenRequest {
    client_id: string;
    username: string;
    password: string;
    grant_type: string;
}

interface OAuthTokenResponse {
    access_token: string;
    token_type: string;
    expires_in: number;
    scope: string;
}

@Injectable()
export class AuthService{

    private readonly headers: Headers = new Headers({
        'Authorization': `Basic ${environment.oauth.basic}`,
        'Content-Type': 'application/x-www-form-urlencoded'
    });

    private urlRedirectTo: string;

    constructor(private http: Http, private router: Router) {
    }

    public setURLRedirectTo(urlRedirectTo: string): void {
        this.urlRedirectTo = urlRedirectTo;
    }

    public login(username: string, password: string): Observable<boolean> {
        let request: OAuthTokenRequest = {
            client_id: environment.oauth.client,
            grant_type: environment.oauth.grant,
            username: username,
            password: password
        };
        return this.http.post(environment.oauth.url + '/token', this.buildFormURLEncoded(request), this.buildRequestOptions())
            .map((response: Response) => {
                let resp: OAuthTokenResponse = response.json();
                if (resp && resp.access_token) {
                    localStorage.setItem(environment.oauth.key, resp.access_token);
                    if (this.urlRedirectTo) {
                        this.router.navigate([this.urlRedirectTo]);
                    } else {
                        this.router.navigate(['/'])
                    }
                    return true;
                } else {
                    return false;
                }
            }).catch((error: any) => Observable.throw(error.json().error || 'Server error'));
    }

    public getToken(): string {
        let token = localStorage.getItem(environment.oauth.key);
        return token ? token : '';
    }

    public logout(urlRedirectTo?: string): void {
        localStorage.removeItem(environment.oauth.key);
        this.setURLRedirectTo(urlRedirectTo);
        this.router.navigate(['/login']);
    }

    public isLoggedIn(): boolean {
        var token: String = this.getToken();
        return token && token.length > 0;
    }


    protected buildRequestOptions(params?: URLParams | URLSearchParams): RequestOptionsArgs {
        return {
            headers: this.headers,
            params: params
        };
    }

    protected buildFormURLEncoded(params: URLParams): string {
        let result: URLSearchParams = new URLSearchParams();
        if (params) {
            for (let key in params) {
                let value = params[key];
                if (value !== undefined) {
                    if (value instanceof Array) {
                        value.forEach(v => {
                            result.append(key, v.toString());
                        });
                    } else {
                        result.append(key, value.toString());
                    }
                }
            }
        }
        return result.toString();
    }
}
