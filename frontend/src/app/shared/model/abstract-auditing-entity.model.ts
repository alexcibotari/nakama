import {AbstractEntity} from './abstract-entity.model';

export abstract class AbstractAuditingEntity extends AbstractEntity{
    createdBy: string;
    createdDate: Date;
    lastModifiedBy: string;
    lastModifiedDate: Date;
}
