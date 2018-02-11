import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';
import {MainComponent} from './main/main.component';

@NgModule({
  imports: [
    RouterModule.forRoot([
      {
        path: 'login', component: LoginComponent
      },
      {
        path: '', component: MainComponent,
        children: [
          {
            path: '', component: DashboardComponent
          },
          {
            path: 'admin', loadChildren: './admin/admin.module#AdminModule'
          }
        ]
      },
    ], {preloadingStrategy: PreloadAllModules})
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
