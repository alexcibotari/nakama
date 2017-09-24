import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import 'hammerjs';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CoreModule} from './core/core.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ConfirmationDialogComponent} from './shared/component/dialog/confirmation-dialog/confirmation-dialog.component';
import {SharedModule} from './shared/shared.module';
import {AuthModule} from './auth/auth.module';
import {LoginComponent} from './login/login.component';
import {ApolloModule} from 'apollo-angular';
import {DateAdapter, MD_DATE_FORMATS, MdDateFormats, NativeDateAdapter} from '@angular/material';
import {ApolloFactoryLoader, ApolloService, getApolloClient} from './core/apollo.service';
import {Router} from '@angular/router';
import {AuthService} from './shared/auth/auth.service';
import './rxjs-operators';

const DATE_FORMATS: MdDateFormats = {
    parse: {
        dateInput: 'YYYY-MM-DD'
    },
    display: {
        dateInput: 'YYYY-MM-DD',
        monthYearLabel: {year: 'numeric', month: 'short'},
        dateA11yLabel: {year: 'numeric', month: 'long', day: 'numeric'},
        monthYearA11yLabel: {year: 'numeric', month: 'long'}
    }
};

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
        /*{provide: DateAdapter, useClass: NativeDateAdapter},
        {provide: MD_DATE_FORMATS, useValue: DATE_FORMATS},*/
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

