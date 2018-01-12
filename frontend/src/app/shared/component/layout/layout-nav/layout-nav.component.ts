import {Component, forwardRef, Inject, OnInit} from '@angular/core';
import {LayoutComponent} from '../layout.component';

@Component({
    selector: 'nkm-layout-nav',
    templateUrl: './layout-nav.component.html',
    styleUrls: ['./layout-nav.component.scss']
})
export class LayoutNavComponent implements OnInit {

    constructor(@Inject(forwardRef(() => LayoutComponent)) public _layout: LayoutComponent) {
    }

    ngOnInit() {
    }

}
