import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';
import {User} from '../../shared/user.model';
import {UserService} from '../../shared/user.service';

@Component({
  moduleId: module.id,
  selector: 'nkm-user-edit',
  templateUrl: 'user-edit.component.html',
  styleUrls: ['user-edit.component.scss']
})
export class UserEditComponent implements OnInit {
  public model: User;
  public loading = true;

  constructor(@Inject(MAT_DIALOG_DATA) public data: User, private userService: UserService) {
  }

  ngOnInit() {
    this.userService.findOne(this.data.login)
      .valueChanges
      .subscribe(value => {
        this.loading = value.loading;
        this.model = value.data.user;
      });
  }

}
