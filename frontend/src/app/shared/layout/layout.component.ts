import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MdDrawerToggleResult, MdSidenav} from "@angular/material";

@Component({
    selector: 'nkm-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

    @ViewChild(MdSidenav) sidenav: MdSidenav;

    @Input() mode: 'over' | 'push' | 'side' = 'over';

    @Input('opened') opened: boolean = false;

    public open(): Promise<MdDrawerToggleResult> {
        return this.sidenav.open();
    }

    public close(): Promise<MdDrawerToggleResult> {
        return this.sidenav.close();
    }

    constructor() {
    }

    ngOnInit() {
    }

}
