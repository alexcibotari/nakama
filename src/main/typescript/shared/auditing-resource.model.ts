import {Resource} from "./resource.model";

export class AuditingResource extends Resource {
    createdBy:string;
    createdDate:Date;
    lastModifiedBy:string;
    lastModifiedDate:Date;
}
