package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.Project;

import javax.validation.constraints.Size;

public class ProjectDTO extends AbstractAuditingDTO<Long> {

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 50)
    private String key;

    @Size(max = 250)
    private String description;

    public ProjectDTO() {
    }

//    public ProjectDTO(Long id, String name, String key, String description) {
//        this.setId(id);
//        this.setName(name);
//        this.setKey(key);
//        this.setDescription(description);
//    }

    public ProjectDTO(Project entity) {
        super(entity);
        this.setName(entity.getName());
        this.setKey(entity.getKey());
        this.setDescription(entity.getDescription());
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
