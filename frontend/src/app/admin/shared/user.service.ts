import {Injectable} from '@angular/core';
import {ApolloService} from '../../core/apollo.service';
import gql from 'graphql-tag';
import {User} from './user.model';

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

interface UsersResponse {
    users: User;
}

@Injectable()
export class UserService {

    constructor(private apollo: ApolloService) {
    }

    public findAll(): void {
        this.apollo.watchQuery<UsersResponse>({
            query: findAll
        }).subscribe((data) => {
            console.log(data);
        });
    }

    private transform(): void {
    }
}
