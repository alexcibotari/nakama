import {AuditingResource} from "../shared/auditing-resource.model";

export class User extends AuditingResource {
    userName: string;
    email: string;
    enabled: boolean;
    authorities: string[];

    constructor(userName: string, email: string) {
        super();
        this.userName = userName;
        this.email = email;
    }
}
