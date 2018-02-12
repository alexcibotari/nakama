import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {Router} from '@angular/router';
import {Apollo, ApolloModule} from 'apollo-angular';
import {AuthService} from './auth.service';
import {LoggerService} from './logger.service';
import {ObjectUtils} from './object-utils.service';
import {HttpHeaders} from '@angular/common/http';
import {onError} from 'apollo-link-error';
import {environment} from '../../environments/environment';
import {InMemoryCache} from 'apollo-cache-inmemory';
import {HttpLink, HttpLinkModule} from 'apollo-angular-link-http';
import {ApolloLink} from 'apollo-link';

@NgModule({
  imports: [
    CommonModule,
    HttpLinkModule,
    ApolloModule
  ],
  exports: [],
  providers: [
    LoggerService,
    ObjectUtils,
  ]
})
export class CoreModule {

  constructor(apollo: Apollo,
              httpLink: HttpLink,
              private auth: AuthService,
              private router: Router) {
    const link = httpLink.create({uri: environment.graphql.uri});

    const authMiddleware = new ApolloLink((operation, forward) => {
      const token = localStorage.getItem(environment.oauth.key);
      if (token) {
        operation.setContext({
          headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
        });
      }
      return forward(operation);
    });

    const logoutLink = onError(errorResponse => {
      if (errorResponse.networkError['status'] === 401) {
        this.auth.logout(this.router.url);
      }
    });

    apollo.create({
      link: ApolloLink.from([authMiddleware, logoutLink, link]),
      cache: new InMemoryCache()
    })
  }
}
