import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../environments/environment';

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

interface URLParams {
  [key: string]: any | any[];
}

const LOGIN_ROUTE = '/login';
const ROOT_ROUTE = '/';

@Injectable()
export class AuthService {

  private readonly headers: HttpHeaders = new HttpHeaders({
    'Authorization': `Basic ${environment.oauth.basic}`,
    'Content-Type': 'application/x-www-form-urlencoded'
  });

  private urlRedirectTo: string;

  constructor(private http: HttpClient, private router: Router) {
  }

  public setURLRedirectTo(urlRedirectTo: string): void {
    this.urlRedirectTo = urlRedirectTo;
  }

  public login(username: string, password: string): Observable<boolean> {
    const request: OAuthTokenRequest = {
      client_id: environment.oauth.client,
      grant_type: environment.oauth.grant,
      username: username,
      password: password
    };
    return this.http.post<OAuthTokenResponse>(
      environment.oauth.url + '/token',
      this.buildFormURLEncoded(request),
      this.buildRequestOptions()
    )
      .map(value => {
        if (value && value.access_token) {
          localStorage.setItem(environment.oauth.key, value.access_token);
          if (this.urlRedirectTo && this.urlRedirectTo !== LOGIN_ROUTE) {
            this.router.navigate([this.urlRedirectTo]);
          } else {
            this.router.navigate([ROOT_ROUTE]);
          }
          return true;
        } else {
          return false;
        }
      }).catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  public getToken(): string {
    const token = localStorage.getItem(environment.oauth.key);
    return token ? token : '';
  }

  public logout(urlRedirectTo?: string): void {
    localStorage.removeItem(environment.oauth.key);
    this.setURLRedirectTo(urlRedirectTo);
    this.router.navigate([LOGIN_ROUTE]);
  }

  public isLoggedIn(): boolean {
    const token: String = this.getToken();
    return token && token.length > 0;
  }


  protected buildRequestOptions(params?: URLParams | URLSearchParams): Object {
    return {
      headers: this.headers,
      params: params
    };
  }

  protected buildFormURLEncoded(params: URLParams): string {
    const result: URLSearchParams = new URLSearchParams();
    if (params) {
      for (const key in params) {
        if (params.hasOwnProperty(key)) {
          const value = params[key];
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
    }
    return result.toString();
  }
}
