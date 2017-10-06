import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {LayoutComponent} from './layout/layout.component';
import {
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatDialogModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatSelectModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule
} from '@angular/material';
import { NavigationDrawerComponent } from './layout/navigation-drawer/navigation-drawer.component';
import {LayoutNavComponent} from './layout/layout-nav/layout-nav.component';
import {CdkTableModule} from '@angular/cdk/table';

const MATERIAL_MODULES: any[] = [
    CdkTableModule,
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatDialogModule,
    MatIconModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatTooltipModule,
    MatSlideToggleModule,
    MatInputModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatSidenavModule,
    MatTableModule,
    MatTabsModule,
    MatSelectModule,
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
