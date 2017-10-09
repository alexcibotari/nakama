import {Injectable} from '@angular/core';
import {ApolloQueryObservable} from 'apollo-angular';
import gql from 'graphql-tag';
import {ApolloService} from '../../core/apollo.service';
import {User} from './user.model';

const findUsersSummery = gql`
    query findUsersSummery{
        users {
            id
            login
            email
            name
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

    constructor(private apollo: ApolloService) {

    }

    public findAllSummary(): ApolloQueryObservable<UsersResponse> {
        return this.apollo.watchQuery<UsersResponse>({query: findUsersSummery});
    }

    public findOne(login: string): ApolloQueryObservable<UserResponse> {
        return this.apollo.watchQuery<UserResponse>({query: findUserByLogin, variables: {login: login}});
    }
}
