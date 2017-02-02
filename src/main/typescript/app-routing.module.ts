import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {ProjectComponent} from "./projects/project.component";

@NgModule({
    imports: [
        RouterModule.forRoot([
            {path: "dashboard", component: DashboardComponent},
            {path: "projects", component: ProjectComponent},
            {path: "", redirectTo: "/dashboard", pathMatch: "full"},
            {path: "**", component: ProjectComponent},
        ])
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {
}
