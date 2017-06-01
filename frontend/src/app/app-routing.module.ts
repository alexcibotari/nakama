import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: DashboardComponent
            },
            {
                path: 'login', component: LoginComponent
            },
            {
                path: 'admin', loadChildren: './admin/admin.module'
            }
        ])
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {
}
