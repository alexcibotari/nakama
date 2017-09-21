import {ApolloClient, createNetworkInterface, WatchQueryOptions} from 'apollo-client';
import {environment} from '../../environments/environment';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Apollo, ApolloQueryObservable} from 'apollo-angular';
import {AuthService} from '../shared/auth/auth.service';

const networkInterface = createNetworkInterface({uri: environment.graphql.uri});

networkInterface.use([{
    applyMiddleware(req, next) {
        if (!req.options.headers) {
            req.options.headers = {};
        }
        const token = localStorage.getItem(environment.oauth.key);
        if (token) {
            req.options.headers.authorization = `Bearer ${token}`;
        }

        next();
    }
}])
    .useAfter([{
        applyAfterware(res, next) {
            if (res.response.status === 401) {
                throw new Error('Unauthorized');
            }
            if (res.response.status === 500) {
                throw new Error('Server Error');
            }
            next();
        }
    }]);

const apolloClient = new ApolloClient({
    networkInterface,
    /*dataIdFromObject: (o: any) => {
        return `${o.__typename}-${o.id}`
    },*/
});

export function ApolloFactoryLoader(auth: AuthService, router: Router) {
    return new ApolloService(auth, router);
}

export function getApolloClient() {
    return apolloClient;
}

@Injectable()
export class ApolloService extends Apollo {

    constructor(private auth: AuthService, private router: Router) {
        super({default: apolloClient});
    }


    /**
     * watchQuery
     *
     * Extends Angular2Apollo watchQuery methods and catches
     * 401 errors.
     *
     */
    watchQuery<T>(options: WatchQueryOptions): ApolloQueryObservable<T> {
        const subscription = super.watchQuery(options);
        subscription
            .subscribe(
                () => {
                },
                err => this.errorHandler(err)
            );
        return subscription;
    }

    /**
     * errorHandler
     *
     * Handles errors thrown
     *
     * @param {Error} err
     * @return {void}
     */
    errorHandler(err: Error): void {
        if (err.toString().indexOf('Unauthorized') !== -1) {
            this.auth.logout(this.router.url);
            console.log('ApolloService Unauthorized');
        } else {
            console.log(err);
            throw err;
        }

    }
}

export {ApolloQueryObservable, ApolloModule} from 'apollo-angular';
