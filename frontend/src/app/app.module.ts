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
import { ApolloClient } from 'apollo-client';
import { ApolloModule } from 'apollo-angular';

const client = new ApolloClient();

export function provideClient(): ApolloClient {
    return client;
}

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

        ApolloModule.forRoot(provideClient)
    ],
    declarations: [AppComponent, DashboardComponent, LoginComponent, ConfirmationDialogComponent],
    entryComponents: [ConfirmationDialogComponent],
    providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
    bootstrap: [AppComponent]
})
export class AppModule {
}
