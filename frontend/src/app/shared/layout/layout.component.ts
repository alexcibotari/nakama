import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MdSidenav} from "@angular/material";

@Component({
    selector: 'nkm-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

    @ViewChild(MdSidenav) sidenav: MdSidenav;

    @Input() mode: 'over' | 'push' | 'side' = 'over';

    @Input('opened') opened: boolean = false;

    constructor() {
    }

    ngOnInit() {
    }

}
