import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {LayoutComponent} from './layout/layout.component';
import {
    MdButtonModule,
    MdCardModule,
    MdCheckboxModule,
    MdDialogModule,
    MdIconModule,
    MdInputModule,
    MdListModule,
    MdMenuModule,
    MdSelectModule,
    MdSidenavModule,
    MdSlideToggleModule,
    MdSnackBarModule,
    MdTableModule,
    MdTabsModule,
    MdToolbarModule,
    MdTooltipModule
} from "@angular/material";
import {CdkTableModule} from "@angular/cdk";
import { NavigationDrawerComponent } from './layout/navigation-drawer/navigation-drawer.component';
import {LayoutNavComponent} from "./layout/layout-nav/layout-nav.component";

const MATERIAL_MODULES: any[] = [
    CdkTableModule,
    MdButtonModule,
    MdCardModule,
    MdDialogModule,
    MdIconModule,
    MdListModule,
    MdMenuModule,
    MdTooltipModule,
    MdSlideToggleModule,
    MdInputModule,
    MdCheckboxModule,
    MdToolbarModule,
    MdSnackBarModule,
    MdSidenavModule,
    MdTableModule,
    MdTabsModule,
    MdSelectModule,
];

const LAYOUT_COMPONENTS: any[] = [
    LayoutComponent,
    LayoutNavComponent,
    NavigationDrawerComponent
];


@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        MATERIAL_MODULES
    ],
    declarations: [LAYOUT_COMPONENTS],
    providers: [],
    exports: [
        MATERIAL_MODULES,
        LAYOUT_COMPONENTS
    ]
})
export class SharedModule {
}
