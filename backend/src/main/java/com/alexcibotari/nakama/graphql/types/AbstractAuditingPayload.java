package com.alexcibotari.nakama.graphql.types;

import java.time.ZonedDateTime;

public abstract class AbstractAuditingPayload extends AbstractPayload {

  private String createdBy;

  private ZonedDateTime createdDate = ZonedDateTime.now();

  private String lastModifiedBy;

  private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public ZonedDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(ZonedDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public ZonedDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }
}
