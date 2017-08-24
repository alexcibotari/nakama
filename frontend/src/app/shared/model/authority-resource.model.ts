import {AuditingResource} from "./auditing-resource.model";

export class AuthorityResource extends AuditingResource{
    givenName: string;
    familyName: string;
    birthday: string;

    fullName: string;

}
