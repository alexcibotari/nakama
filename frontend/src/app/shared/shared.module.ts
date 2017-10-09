import {CdkTableModule} from '@angular/cdk/table';
import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
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
    MatProgressBarModule,
    MatSelectModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule
} from '@angular/material';
import {LayoutNavComponent} from './component/layout/layout-nav/layout-nav.component';
import {LayoutComponent} from './component/layout/layout.component';
import {NavigationDrawerComponent} from './component/layout/navigation-drawer/navigation-drawer.component';

const MATERIAL_MODULES: any[] = [
    CdkTableModule,
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
    MatProgressBarModule,
    MatSelectModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule
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
