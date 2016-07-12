package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.Issue;

public class IssueDTO {

    private Long id;

    private String key;

    private String summery;

    private String description;

    public IssueDTO() {
    }

    public IssueDTO(Long id, String key, String summery, String description) {
        this.setId(id);
        this.setKey(key);
        this.setSummery(summery);
        this.setDescription(description);
    }

    public IssueDTO(Issue issue) {
        this(issue.getId(), issue.getKey(), issue.getSummery(), issue.getDescription());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
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
}
