import {AuditingResource} from '../../shared/model/auditing-resource.model';

export class Project extends AuditingResource {
    name: string;
    description: string;

    constructor(id: string, name: string, description: string) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
