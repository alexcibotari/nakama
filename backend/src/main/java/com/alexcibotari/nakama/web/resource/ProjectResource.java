package com.alexcibotari.nakama.web.resource;

import org.springframework.hateoas.core.Relation;

import javax.validation.constraints.Size;

@Relation(value = "project", collectionRelation = "projects")
public class ProjectResource extends AbstractAuditingResource {

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 50)
    private String key;

    @Size(max = 250)
    private String description;

    public ProjectResource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
            "name='" + name + '\'' +
            ", key='" + key + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
