import {Injectable} from '@angular/core';
import {ApolloService} from '../../core/apollo.service';
import gql from 'graphql-tag';
import {User} from './user.model';
import {ApolloQueryObservable} from "apollo-angular";

const findAll = gql`
    query users {
        users {
            id
            login
            email
            personal {
                givenName
                familyName
                birthday
            }
        }
    }`;

export interface UsersResponse {
    users: User[];
}

@Injectable()
export class UserService {

    constructor(private apollo: ApolloService) {
    }

    public findAll():  ApolloQueryObservable<UsersResponse>{
        return this.apollo.watchQuery<UsersResponse>({query: findAll});
    }
}
