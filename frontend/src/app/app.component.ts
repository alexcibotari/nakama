import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import './rxjs-operators';

@Component({
    moduleId: module.id,
    selector: 'nkm-root',
    templateUrl: 'app.component.html',
    styleUrls: ['app.component.scss']
})
export class AppComponent implements OnInit {

    userRoutes = [
        {
            title: 'Dashboard',
            route: '/',
            icon: 'widgets',
        }
    ];

    contentRoutes = [
        {
            title: 'Pages',
            route: '/',
            icon: 'pages',
        }, {
            title: 'Design',
            route: '/',
            icon: 'format_paint'
        }, {
            title: 'Analytics',
            route: '/',
            icon: 'timeline'
        }
    ];

    commerceRoutes = [
        {
            title: 'Products',
            route: '/',
            icon: 'widgets',
        }, {
            title: 'Categories',
            route: '/',
            icon: 'widgets'
        }, {
            title: 'Discounts',
            route: '/',
            icon: 'money_off'
        }, {
            title: 'Orders',
            route: '/',
            icon: 'shopping_cart'
        }
    ];

    adminRoutes = [
        {
            title: 'User Management',
            route: '/admin/users',
            icon: 'people',
        }, {
            title: 'Administration',
            route: '/',
            icon: 'widgets'
        }
    ];

    otherRoutes = [
        {
            title: 'Settings',
            route: '/',
            icon: 'settings',
        }, {
            title: 'Help',
            route: '/',
            icon: 'help'
        }
    ];

    constructor(private _router: Router) {
    }

    ngOnInit() {
    }
}
