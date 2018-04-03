export abstract class AbstractEntity {
  id: string;
}

export abstract class AbstractAuditingEntity extends AbstractEntity {
  createdBy: string;
  createdDate: Date;
  lastModifiedBy: string;
  lastModifiedDate: Date;
}
