import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatTableDataSource} from '@angular/material';
import {ConfirmationDialogComponent} from '../../../shared/component/dialog/confirmation-dialog/confirmation-dialog.component';
import {User} from '../../shared/user.model';
import {UserDetailComponent} from '../user-detail/user-detail.component';
import {UserEditComponent} from '../user-edit/user-edit.component';
import {UserService} from '../../shared/user.service';

@Component({
  moduleId: module.id,
  selector: 'nkm-user-list',
  templateUrl: 'user-list.component.html',
  styleUrls: ['user-list.component.scss'],
  providers: []
})
export class UserListComponent implements OnInit {
  displayedColumns: String[] = ['avatar', 'login', 'email', 'enabled', 'actions'];
  dataSource: MatTableDataSource<User> = new MatTableDataSource();

  @ViewChild('filter') filter: ElementRef;

  constructor(private readonly userService: UserService, private readonly dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.userService.findAllSummary().subscribe(value => this.dataSource.data = value);
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  toDetail(user: User) {
    const dialogRef = this.dialog.open(UserDetailComponent, {data: user});
    dialogRef.afterClosed().subscribe((value: User) => {
      console.log(value);
    });
  }

  toEdit(user: User) {
    const dialogRef = this.dialog.open(UserEditComponent, {data: user});
    dialogRef.afterClosed().subscribe((value: User) => {
      console.log(value);
    });
  }

  toDelete(user: User) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {data: {content: `Delete ${user.login} user ?`}});
    dialogRef.afterClosed().subscribe(value => {
      console.log(value);
    });
  }
}
