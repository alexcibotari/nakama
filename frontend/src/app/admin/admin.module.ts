import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {FormsModule} from '@angular/forms';
import {MaterialModule} from '@angular/material';
import {RouterModule} from '@angular/router';
import {UserService} from '../shared/service/user-rest.service';
import {AdminRoutingModule} from './admin-routing.module';
import {AdminComponent} from './admin.component';
import {UserDetailComponent} from './users/user-detail.component';
import {UserListComponent} from './users/user-list.component';
import {UsersComponent} from './users/users.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        MaterialModule,
        FlexLayoutModule,
        AdminRoutingModule,
        RouterModule
    ],
    declarations: [AdminComponent, UsersComponent, UserListComponent, UserDetailComponent],
    providers: [UserService]
})
export default class AdminModule {
}
