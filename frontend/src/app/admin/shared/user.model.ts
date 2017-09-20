import {Personal} from './personal.model';
import {AuditingResource} from '../../shared/model/auditing-resource.model';

export class User extends AuditingResource {
    login: string;
    email: string;

    personal: Personal;

    enabled: boolean;
    authorities: Array<string>;

    constructor(login: string, email: string) {
        super();
        this.login = login;
        this.email = email;
    }
}
