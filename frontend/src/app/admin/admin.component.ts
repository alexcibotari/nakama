import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import './../rxjs-operators';

@Component({
    moduleId: module.id,
    selector: 'admin-root',
    templateUrl: 'admin.component.html',
    styleUrls: ['admin.component.css']
})
export class AdminComponent implements OnInit {
    routes: Object[] = [
        {
            title: 'aaaaa',
            route: '/',
            icon: 'dashboard',
        }, {
            title: 'bbbbbb',
            route: '/admin',
            icon: 'supervisor_account'
        }
    ];

    constructor(private _router: Router) {
    }

    ngOnInit() {
    }
}
