package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.Issue;

public class IssueDTO {

    private Long id;

    private Long key;

    private String summery;

    private String description;

    private Long project;

    public IssueDTO() {
    }

    public IssueDTO(Long id, Long key, Long project, String summery, String description) {
        this.setId(id);
        this.setKey(key);
        this.setProject(project);
        this.setSummery(summery);
        this.setDescription(description);
    }

    public IssueDTO(Issue issue) {
        this(issue.getId(), issue.getKey(), issue.getProject().getId(), issue.getSummery(), issue.getDescription());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }
}
