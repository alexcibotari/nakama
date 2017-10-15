import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'nkm-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss'],
})
export class MainComponent {

    userRoutes = [
        {
            title: 'Dashboard',
            route: '/',
            icon: 'widgets',
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

  constructor(private _router: Router) {}

}
