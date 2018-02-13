import {Injectable} from '@angular/core';
import gql from 'graphql-tag';
import {User} from './user.model';
import {Apollo} from 'apollo-angular';
import {Observable} from "rxjs/Observable";

const findUsersSummery = gql`
  query findUsersSummery{
    users {
      id
      login
      email
      name
      enabled
    }
  }`;

export interface UsersResponse {
  users: User[];
}

const findUserByLogin = gql`
  query findUserByLogin($login: String!){
    user (login : $login) {
      id
      login
      email
      name
      birthday
      familyName
      givenName
      authorities{
        name
      }
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
    .map(result => result.data.users);
  }

  public findOne(login: string): Observable<User> {
    return this.apollo.query<UserResponse>(
      {query: findUserByLogin, variables: {login: login}}
    )
    .map(result => result.data.user);
  }
}
