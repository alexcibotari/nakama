import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {CollectionViewer, DataSource} from '@angular/cdk/collections';
import {UserService} from './user.service';
import {User} from './user.model';

@Injectable()
export class UserDataSource extends DataSource<User> {

    private _dataChanges: BehaviorSubject<User[]> = new BehaviorSubject<User[]>([]);

    _filterChange = new BehaviorSubject('');

    get filter(): string {
        return this._filterChange.value;
    }

    set filter(filter: string) {
        this._filterChange.next(filter);
    }

    constructor(private userService: UserService) {
        super();
    }

    connect(collectionViewer: CollectionViewer): Observable<User[]> {
        this.userService.findAllSummary()
            .subscribe((data) => {
                this._dataChanges.next(data.data.users);
                console.log(data);
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
