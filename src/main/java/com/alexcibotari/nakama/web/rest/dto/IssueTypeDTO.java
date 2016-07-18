package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueType;

public class IssueTypeDTO {

    private Long id;

    private String name;

    private String description;

    public IssueTypeDTO() {
    }

    public IssueTypeDTO(Long id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public IssueTypeDTO(IssueType priority) {
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
