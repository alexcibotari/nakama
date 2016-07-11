package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.Project;

public class ProjectDTO {

    private Long id;

    private String name;

    private String key;

    private String description;

    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String name, String key, String description) {
        this.setId(id);
        this.setName(name);
        this.setKey(key);
        this.setDescription(description);
    }

    public ProjectDTO(Project project) {
        this.setId(project.getId());
        this.setName(project.getName());
        this.setKey(project.getKey());
        this.setDescription(project.getDescription());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
