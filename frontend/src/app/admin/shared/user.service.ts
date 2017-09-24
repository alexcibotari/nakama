import {Injectable} from '@angular/core';
import {ApolloQueryObservable} from 'apollo-angular';
import gql from 'graphql-tag';
import {ApolloService} from '../../core/apollo.service';
import {User} from './user.model';

const allSummary = gql`
    {
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

@Injectable()
export class UserService {

    constructor(private apollo: ApolloService) {

    }

    public findAllSummary(): ApolloQueryObservable<UsersResponse> {
        return this.apollo.watchQuery<UsersResponse>({query: allSummary});
    }
}
