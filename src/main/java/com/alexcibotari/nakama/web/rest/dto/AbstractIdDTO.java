package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.AbstractIdEntity;

public abstract class AbstractIdDTO <T extends Object>{

    AbstractIdDTO() {
    }

    AbstractIdDTO(AbstractIdEntity entity) {
        this.setId((T)entity.getId());
    }

    private T id;

    public T getId() {
        return this.id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public abstract String toString();

    public int hashCode() {
        return toString().hashCode();
    }
}
