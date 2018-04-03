import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DateAdapter, NativeDateAdapter} from '@angular/material';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import 'hammerjs';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthModule} from './auth/auth.module';
import {CoreModule} from './core/core.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';
import {MainComponent} from './main/main.component';
import './rxjs-operators';
import {ConfirmationDialogComponent} from './shared/component/dialog/confirmation-dialog/confirmation-dialog.component';
import {SharedModule} from './shared/shared.module';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CoreModule,
    AppRoutingModule,
    SharedModule,
    AuthModule.forRoot(),
    FlexLayoutModule,
  ],
  declarations: [AppComponent, MainComponent, DashboardComponent, LoginComponent, ConfirmationDialogComponent],
  entryComponents: [ConfirmationDialogComponent],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy},
    {provide: DateAdapter, useClass: NativeDateAdapter}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
