import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {AdminRoutingModule} from './admin-routing.module';
import {AdminComponent} from './admin.component';
import {UserDetailComponent} from './users/user-detail/user-detail.component';
import {UserListComponent} from './users/user-list/user-list.component';
import {UsersComponent} from './users/users.component';
import {SharedModule} from '../shared/shared.module';
import {AuthModule} from '../auth/auth.module';
import {UserEditComponent} from './users/user-edit/user-edit.component';
import {UserService} from './shared/user.service';

@NgModule({
  imports: [
    SharedModule,
    CommonModule,
    AdminRoutingModule,
    AuthModule
  ],
  declarations: [AdminComponent, UsersComponent, UserListComponent, UserDetailComponent, UserEditComponent],
  entryComponents: [UserDetailComponent, UserEditComponent],
  providers: [UserService]
})
export class AdminModule {
}
