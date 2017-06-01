import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import './rxjs-operators';

@Component({
    moduleId: module.id,
    selector: 'app-root',
    templateUrl: 'app.component.html',
    styleUrls: ['app.component.css']
})
export class AppComponent implements OnInit {

    routes: Object[] = [
        {
            title: 'Dashboard',
            route: '/',
            icon: 'dashboard',
        }, {
            title: 'Administration',
            route: '/admin',
            icon: 'supervisor_account'
        }
    ];

    constructor(private _router: Router) {
    }

    ngOnInit() {
    }
}
