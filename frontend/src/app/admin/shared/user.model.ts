import {AbstractAuditingEntity} from '../../shared/model/entity.model';

export class User extends AbstractAuditingEntity {
    login: string;
    enabled: boolean;
    email: string;
    authorities?: Array<string>;

    name: string;
    givenName: string;
    familyName: string;
    birthday: string;
    gender: string;

    jobTitle: string;
    telephone: string;
}
