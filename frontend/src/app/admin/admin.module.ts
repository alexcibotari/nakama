import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {FormsModule} from '@angular/forms';
import {
    MdAutocompleteModule,
    MdButtonModule,
    MdButtonToggleModule,
    MdCardModule,
    MdCheckboxModule,
    MdChipsModule,
    MdCoreModule,
    MdDatepickerModule,
    MdDialogModule,
    MdExpansionModule,
    MdGridListModule,
    MdIconModule,
    MdInputModule,
    MdListModule,
    MdMenuModule,
    MdNativeDateModule,
    MdPaginatorModule,
    MdProgressBarModule,
    MdProgressSpinnerModule,
    MdRadioModule,
    MdRippleModule,
    MdSelectModule,
    MdSidenavModule,
    MdSliderModule,
    MdSlideToggleModule,
    MdSnackBarModule,
    MdSortModule,
    MdTableModule,
    MdTabsModule,
    MdToolbarModule,
    MdTooltipModule
} from '@angular/material';
import {AdminRoutingModule} from './admin-routing.module';
import {AdminComponent} from './admin.component';
import {UserDetailComponent} from './users/user-detail/user-detail.component';
import {UserListComponent} from './users/user-list/user-list.component';
import {UsersComponent} from './users/users.component';
import {CdkTableModule} from "@angular/cdk";
import {SharedModule} from "../shared/shared.module";
import {AuthModule} from "../auth/auth.module";
import {UserService} from "./shared/user-rest.service";
import {UserDataSource} from "./shared/user-data-source.service";

@NgModule({
    imports: [
        SharedModule,
        CommonModule,
        FormsModule,
        FlexLayoutModule,
        AdminRoutingModule,
        AuthModule
    ],
    declarations: [AdminComponent, UsersComponent, UserListComponent, UserDetailComponent],
    providers: [UserService, UserDataSource]
})
export class AdminModule {
}
