import {Component, OnInit} from '@angular/core';
import {LoginModel} from './login.model';
import {AuthService} from '../core/auth.service';

@Component({
  selector: 'nkm-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  model: LoginModel = new LoginModel('admin', 'admin');

  constructor(private readonly authService: AuthService) {
  }

  ngOnInit() {
  }

  login() {
    this.authService.login(this.model.login, this.model.password).subscribe();
  }

}
