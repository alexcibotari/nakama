package com.alexcibotari.nakama.web.rest.dto;

public abstract class AbstractIdDTO {

    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract String toString();

    public int hashCode() {
        return toString().hashCode();
    }
}
