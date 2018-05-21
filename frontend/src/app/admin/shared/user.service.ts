import {Injectable} from '@angular/core';
import {Apollo} from 'apollo-angular';
import gql from 'graphql-tag';
import {Observable} from 'rxjs';
import {User} from './user.model';
import {map} from 'rxjs/operators';

const findUsersSummery = gql`
  query findUsersSummery{
    users {
      login
      email
      enabled
    }
  }`;

export interface UsersResponse {
  users: User[];
}

const findUserByLogin = gql`
  query findUserByLogin($login: String!){
    user (login : $login) {
      login
      email
      enabled
    }
  }`;

export interface UserResponse {
  user: User;
}

@Injectable()
export class UserService {

  constructor(private readonly apollo: Apollo) {

  }

  public findAllSummary(): Observable<User[]> {
    return this.apollo.query<UsersResponse>({query: findUsersSummery})
    .pipe(
      map(result => result.data.users)
    );
  }

  public findOne(login: string): Observable<User> {
    return this.apollo.query<UserResponse>(
      {query: findUserByLogin, variables: {login: login}}
    )
    .pipe(
      map(result => result.data.user)
    );
  }
}
