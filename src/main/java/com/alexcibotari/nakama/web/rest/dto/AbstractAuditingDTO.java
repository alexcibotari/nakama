package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.AbstractAuditingEntity;

import java.time.ZonedDateTime;

public abstract class AbstractAuditingDTO<T> extends AbstractIdDTO<T> {

    AbstractAuditingDTO() {
    }

    AbstractAuditingDTO(AbstractAuditingEntity entity) {
        super(entity);
        this.setCreatedDate(entity.getCreatedDate());
        this.setLastModifiedDate(entity.getLastModifiedDate());
    }

    private String createdBy;

    private ZonedDateTime createdDate;

    private String lastModifiedBy;

    private ZonedDateTime lastModifiedDate;

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
