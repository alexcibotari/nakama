import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatSidenav} from '@angular/material';
import {MatDrawerToggleResult} from "@angular/material/sidenav/typings/drawer";

@Component({
  selector: 'nkm-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  @ViewChild(MatSidenav) sidenav: MatSidenav;

  @Input() mode: 'over' | 'push' | 'side' = 'over';

  @Input('opened') opened: Boolean = false;

  public open(): Promise<MatDrawerToggleResult> {
    return this.sidenav.open();
  }

  public close(): Promise<MatDrawerToggleResult> {
    return this.sidenav.close();
  }

  constructor() {
  }

  ngOnInit() {
  }

}
