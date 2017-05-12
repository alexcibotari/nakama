import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {FormsModule} from '@angular/forms';
import {MaterialModule} from '@angular/material';
import {CovalentCoreModule} from '@covalent/core';
import {UserService} from '../shared/service/user-rest.service';
import {UserDetailComponent} from './user-detail.component';
import {UserListComponent} from './user-list.component';
import {UsersRoutingModule} from './users-routing.module';
import {UsersComponent} from './users.component';

@NgModule({
    imports: [
        CommonModule,
        CovalentCoreModule,
        FormsModule,
        MaterialModule,
        FlexLayoutModule,
        UsersRoutingModule
    ],
    providers: [UserService],
    declarations: [UsersComponent, UserListComponent, UserDetailComponent]
})
export default class UsersModule {
}
