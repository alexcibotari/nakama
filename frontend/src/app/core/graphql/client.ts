import { ApolloClient, createNetworkInterface } from 'apollo-client';
import {environment} from "../../../environments/environment";

const networkInterface = createNetworkInterface({uri :environment.graphql.uri});

networkInterface.use([{
    applyMiddleware(req, next) {
        if (!req.options.headers) {
            req.options.headers = {};
        }
        let token = localStorage.getItem(environment.oauth.key);
        if(token){
            req.options.headers.authorization = `Bearer ${token}`;
        }

        next();
    }
}])
    .useAfter([{
        applyAfterware({ response }, next) {
            //console.log(response);
            if (response.status === 401) {
            }
            next();
        }
    }]);

const client = new ApolloClient({networkInterface});

export function provideClient(): ApolloClient {
    return client;
}
