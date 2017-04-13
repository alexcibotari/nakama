import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserDetailComponent} from './user-detail.component';
import {UserListComponent} from './user-list.component';
import {UsersComponent} from './users.component';

const usersRoutes: Routes = [
    {
        path: '', component: UsersComponent,
        children: [
            {path: '', component: UserListComponent},
            {path: ':id', component: UserDetailComponent}
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(usersRoutes)],
    exports: [RouterModule]
})
export class UsersRoutingModule {
}
