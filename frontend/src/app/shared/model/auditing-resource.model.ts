import {Resource} from '../../core/web/http/hal/resource.model';

export abstract class AuditingResource extends Resource {
    createdBy: string;
    createdDate: Date;
    lastModifiedBy: string;
    lastModifiedDate: Date;
}
