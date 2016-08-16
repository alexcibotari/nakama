package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.AbstractIdEntity;

import java.io.Serializable;

public abstract class AbstractIdDTO <ID extends Serializable>{

    AbstractIdDTO() {
    }

    AbstractIdDTO(AbstractIdEntity entity) {
        this.setId((ID)entity.getId());
    }

    private ID id;

    public ID getId() {
        return this.id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public abstract String toString();

    public int hashCode() {
        return toString().hashCode();
    }
}
