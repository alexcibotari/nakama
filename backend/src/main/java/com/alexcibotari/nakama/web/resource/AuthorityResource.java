package com.alexcibotari.nakama.web.resource;

import javax.validation.constraints.Size;

public class AuthorityResource extends AbstractAuditingResource {

    @Size(min = 3, max = 50)
    private String name;

    public AuthorityResource() {
    }

    public AuthorityResource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "AuthorityResource{" +
            "name='" + name + '\'' +
            '}';
    }
}
