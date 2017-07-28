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


@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        MATERIAL_MODULES
    ],
    declarations: [LayoutComponent],
    exports: [
        MATERIAL_MODULES
    ]
})
export class SharedModule {
}
