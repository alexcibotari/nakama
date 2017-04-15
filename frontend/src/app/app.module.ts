import {CommonModule, HashLocationStrategy, LocationStrategy} from '@angular/common';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {FormsModule} from '@angular/forms';
import {HttpModule, JsonpModule} from '@angular/http';
import {MaterialModule} from '@angular/material';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule} from '@angular/router';
import {CovalentCoreModule} from '@covalent/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CoreModule} from './core/core.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {MainComponent} from './main/main.component';
import {ProjectComponent} from './projects/project.component';

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
        CovalentCoreModule,
        CoreModule,
        AppRoutingModule,
        RouterModule
    ],
    declarations: [AppComponent, MainComponent, DashboardComponent, ProjectComponent],
    providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
    bootstrap: [AppComponent]
})
export class AppModule {
}
