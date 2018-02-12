import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import {UserService} from './user.service';
import {User} from './user.model';

@Injectable()
export class UserDataSource extends DataSource<User> {

  private readonly _dataChanges: BehaviorSubject<User[]> = new BehaviorSubject<User[]>([]);

  _filterChange = new BehaviorSubject('');

  get filter(): string {
    return this._filterChange.value;
  }

  set filter(filter: string) {
    this._filterChange.next(filter);
  }

  constructor(private readonly userService: UserService) {
    super();
  }

  connect(collectionViewer: CollectionViewer): Observable<User[]> {
    this.userService.findAllSummary()
      .valueChanges
      .subscribe((value) => {
        this._dataChanges.next(value.data.users);
        console.log(value);
      });
    return Observable.merge(...[this._dataChanges, this._filterChange]).map(() => {
      return this._dataChanges.getValue().slice()
        .filter((item: User) => {
          return (item.login + item.email + item.name)
            .toLowerCase().indexOf(this._filterChange.getValue().toLowerCase()) !== -1;
        });
    });
  }

  disconnect(collectionViewer: CollectionViewer): void {
  }

}
