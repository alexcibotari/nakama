import {AbstractAuditingEntity} from '../../shared/model/abstract-auditing-entity.model';

export class User extends AbstractAuditingEntity {
    login: string;
    enabled: boolean;
    authorities: Array<string>;

    email: string;
    name: string;
    givenName: string;
    familyName: string;
    birthday: string;
    gender: string;

    jobTitle: string;
    telephone: string;
}
