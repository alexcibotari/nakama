import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import './../rxjs-operators';

@Component({
    moduleId: module.id,
    selector: 'admin-component',
    templateUrl: 'admin.component.html',
    styleUrls: ['admin.component.css']
})
export class AdminComponent implements OnInit {
    routes = [
        {
            title: 'Users',
            route: '/admin/users',
            icon: 'group',
        }, {
            title: 'Settings',
            route: '/settings',
            icon: 'settings'
        }
    ];

    constructor(private _router: Router) {
    }

    ngOnInit() {
    }
}
