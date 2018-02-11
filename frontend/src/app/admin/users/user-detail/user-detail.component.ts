import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material';
import {User} from '../../shared/user.model';
import {UserService} from '../../shared/user.service';
import {UserEditComponent} from '../user-edit/user-edit.component';

@Component({
  moduleId: module.id,
  selector: 'nkm-user-detail',
  templateUrl: 'user-detail.component.html',
  styleUrls: ['user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
  public model: User;
  public loading = true;

  constructor(@Inject(MAT_DIALOG_DATA) public data: User, private dialog: MatDialog, private userService: UserService) {
  }

  ngOnInit() {
    this.userService.findOne(this.data.login)
      .valueChanges
      .subscribe(value => {
        this.loading = value.loading;
        this.model = value.data.user;
      });
  }

  toEdit() {
    const dialogRef = this.dialog.open(UserEditComponent, {data: this.model});
    dialogRef.afterClosed().subscribe((value: User) => {
      console.log(value);
    });
  }

}
