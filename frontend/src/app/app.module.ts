import {CommonModule, HashLocationStrategy, LocationStrategy} from '@angular/common';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {FormsModule} from '@angular/forms';
import {HttpModule, JsonpModule} from '@angular/http';
import {MaterialModule} from '@angular/material';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule} from '@angular/router';
import 'hammerjs';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CoreModule} from './core/core.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';
import {OAuthService} from './shared/service/oauth.service';
import { ConfirmationDialogComponent } from './shared/component/dialog/confirmation-dialog/confirmation-dialog.component';

@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        CommonModule,
        FormsModule,
        HttpModule,
        JsonpModule,
        MaterialModule,
        FlexLayoutModule,
        CoreModule,
        AppRoutingModule,
        RouterModule
    ],
    declarations: [AppComponent, DashboardComponent, LoginComponent, ConfirmationDialogComponent],
    entryComponents: [ConfirmationDialogComponent],
    providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}, OAuthService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
