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

  constructor(@Inject(MAT_DIALOG_DATA) public data: User,
              private readonly userService: UserService) {
  }

  ngOnInit() {
    this.userService.findOne(this.data.login)
    .subscribe(value => {
      this.loading = false;
      this.model = value;
    });
  }

}
