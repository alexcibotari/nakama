import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminComponent} from './admin.component';
import {UserDetailComponent} from './users/user-detail/user-detail.component';
import {UserListComponent} from './users/user-list/user-list.component';
import {UsersComponent} from './users/users.component';

const usersRoutes: Routes = [
  {
    path: '', component: AdminComponent,
    children: [
      {path: '', redirectTo: 'users', pathMatch: 'full'},
      {
        path: 'users', component: UsersComponent,
        children: [
          {path: '', component: UserListComponent},
          {path: ':id', component: UserDetailComponent}
        ]
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(usersRoutes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
