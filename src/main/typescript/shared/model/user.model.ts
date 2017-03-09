import {AuditingResource} from './auditing-resource.model';

export class User extends AuditingResource {
    login: string;
    email: string;
    enabled: boolean;
    authorities: Array<string>;

    constructor(login: string, email: string) {
        super();
        this.login = login;
        this.email = email;
    }
}
