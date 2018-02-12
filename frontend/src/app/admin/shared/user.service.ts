import {Injectable} from '@angular/core';
import gql from 'graphql-tag';
import {User} from './user.model';
import {Apollo, QueryRef} from 'apollo-angular';

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

  public findAllSummary(): QueryRef<UsersResponse> {
    return this.apollo.watchQuery<UsersResponse>({query: findUsersSummery});
  }

  public findOne(login: string): QueryRef<UserResponse> {
    return this.apollo.watchQuery<UserResponse>({query: findUserByLogin, variables: {login: login}});
  }
}
