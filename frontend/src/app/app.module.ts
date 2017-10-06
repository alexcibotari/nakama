import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {DateAdapter, NativeDateAdapter} from '@angular/material';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Router} from '@angular/router';
import {ApolloModule} from 'apollo-angular';
import 'hammerjs';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthModule} from './auth/auth.module';
import {ApolloFactoryLoader, ApolloService, getApolloClient} from './core/apollo.service';
import {CoreModule} from './core/core.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';
import './rxjs-operators';
import {AuthService} from './shared/auth/auth.service';
import {ConfirmationDialogComponent} from './shared/component/dialog/confirmation-dialog/confirmation-dialog.component';
import {SharedModule} from './shared/shared.module';

@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule,
        HttpModule,

        CoreModule,
        AppRoutingModule,
        SharedModule,
        AuthModule.forRoot(),
        FlexLayoutModule,
        ApolloModule.forRoot(getApolloClient)
    ],
    declarations: [AppComponent, DashboardComponent, LoginComponent, ConfirmationDialogComponent],
    entryComponents: [ConfirmationDialogComponent],
    providers: [
        {provide: LocationStrategy, useClass: HashLocationStrategy},
        {provide: DateAdapter, useClass: NativeDateAdapter},
        {
            provide: ApolloService,
            useFactory: ApolloFactoryLoader,
            deps: [AuthService, Router],
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}

