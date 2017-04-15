import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {MainComponent} from './main/main.component';

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: MainComponent,
                children: [
                    {
                        path: '', component: DashboardComponent
                    },
                    {
                        path: 'users', loadChildren:  './users/users.module'
                    }
                ]
            }
        ])
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {
}
