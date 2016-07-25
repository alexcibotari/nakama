package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueStatus;

public class IssueStatusDTO {

    private Long id;

    private String name;

    private String description;

    public IssueStatusDTO() {
    }

    public IssueStatusDTO(Long id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public IssueStatusDTO(IssueStatus priority) {
        this(priority.getId(), priority.getName(), priority.getDescription());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
