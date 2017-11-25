import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {Router} from '@angular/router';
import {ApolloModule} from 'apollo-angular';
import {ApolloFactoryLoader, ApolloService, getApolloClient} from './apollo.service';
import {AuthService} from './auth.service';
import {LoggerService} from './logger.service';
import {ObjectUtils} from './object-utils.service';

@NgModule({
    imports: [
        CommonModule,
        ApolloModule.forRoot(getApolloClient)
    ],
    exports: [],
    providers: [
        LoggerService,
        ObjectUtils,
        {
            provide: ApolloService,
            useFactory: ApolloFactoryLoader,
            deps: [AuthService, Router],
        }
    ]
})
export class CoreModule {
}
