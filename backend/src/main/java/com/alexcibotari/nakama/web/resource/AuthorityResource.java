package com.alexcibotari.nakama.web.resource;


import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import javax.validation.constraints.Size;
import java.util.Set;

@Relation(value = "authority", collectionRelation = "authorities")
public class AuthorityResource extends ResourceSupport {

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

    @Override
    public String toString() {
        return "AuthorityResource{" +
            "name='" + name + '\'' +
            '}';
    }
}
