import {AuditingResource} from "./auditing-resource.model";
import {PersonalResource} from "./personal-resource.model";

export class UserResource extends AuditingResource {
    login: string;
    email: string;

    personal: PersonalResource;

    enabled: boolean;
    authorities: Array<string>;

    constructor(login: string, email: string) {
        super();
        this.login = login;
        this.email = email;
    }
}
