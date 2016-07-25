package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.Project;

import java.time.ZonedDateTime;

public class ProjectDTO {

    private Long id;

    private String name;

    private String key;

    private String description;

    private ZonedDateTime createdDate;

    private ZonedDateTime lastModifiedDate;

    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String name, String key, String description) {
        this.setId(id);
        this.setName(name);
        this.setKey(key);
        this.setDescription(description);
    }

    public ProjectDTO(Project project) {
        this(project.getId(), project.getName(), project.getKey(), project.getDescription());
        this.setCreatedDate(project.getCreatedDate());
        this.setLastModifiedDate(project.getLastModifiedDate());
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

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
