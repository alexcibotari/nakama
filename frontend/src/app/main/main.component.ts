import {Component} from '@angular/core';

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

  constructor() {
  }

}
