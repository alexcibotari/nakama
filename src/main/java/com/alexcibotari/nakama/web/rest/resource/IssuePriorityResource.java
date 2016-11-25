package com.alexcibotari.nakama.web.rest.resource;

import org.springframework.hateoas.core.Relation;

import javax.validation.constraints.Size;

@Relation(value = "issuePriority", collectionRelation = "issuePriorities")
public class IssuePriorityResource extends AbstractAuditingResource {

    @Size(min = 3, max = 50)
    private String name;

    @Size(max = 250)
    private String description;

    public IssuePriorityResource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "IssuePriorityResource{" +
            "id='" + getId() + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
