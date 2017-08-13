import {AuditingResource} from "./auditing-resource.model";
import {PersonalResource} from "./personal-resource.model";

export class UserResource extends AuditingResource {
    name: string;
    email: string;

    personal: PersonalResource;

    enabled: boolean;
    authorities: Array<string>;

    constructor(name: string, email: string) {
        super();
        this.name = name;
        this.email = email;
    }
}
