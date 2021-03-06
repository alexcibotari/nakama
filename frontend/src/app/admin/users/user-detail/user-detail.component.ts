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

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: User,
    private readonly dialog: MatDialog,
    private readonly userService: UserService
  ) {
  }

  ngOnInit() {
    this.userService.findOne(this.data.login)
      .subscribe(value => {
        this.loading = false;
        this.model = value;
      });
  }

  toEdit() {
    const dialogRef = this.dialog.open(UserEditComponent, {data: this.model});
    dialogRef.afterClosed().subscribe((value: User) => {
      console.log(value);
    });
  }

}
