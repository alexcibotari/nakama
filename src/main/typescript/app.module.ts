import {CommonModule, LocationStrategy, HashLocationStrategy} from "@angular/common";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule, JsonpModule} from "@angular/http";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {AppRoutingModule} from "./app-routing.module";
import {CoreModule} from "./core/core.module";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {ProjectComponent} from "./projects/project.component";
import {RouterModule} from "@angular/router";

@NgModule({
    imports: [
        BrowserModule,
        CommonModule,
        FormsModule,
        HttpModule,
        JsonpModule,
        CoreModule,
        AppRoutingModule,
        RouterModule],
    declarations: [AppComponent, DashboardComponent, ProjectComponent],
    providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
    bootstrap: [AppComponent]
})
export class AppModule {
}
