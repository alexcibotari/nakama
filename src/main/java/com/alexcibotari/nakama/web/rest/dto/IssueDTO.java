package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.Issue;

public class IssueDTO {

    //Composite key of Project Key and Issue IdInProject
    private String id;

    private Long idInProject;

    private String project;

    private String summery;

    private String description;

    public IssueDTO() {
    }

    public IssueDTO(Long idInProject, String project, String summery, String description) {
        this.setId(project + "-" + idInProject);
        this.setIdInProject(idInProject);
        this.setProject(project);
        this.setSummery(summery);
        this.setDescription(description);
    }

    public IssueDTO(Issue issue) {
        this(issue.getIdInProject(), issue.getProject().getKey(), issue.getSummery(), issue.getDescription());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdInProject() {
        return idInProject;
    }

    public void setIdInProject(Long idInProject) {
        this.idInProject = idInProject;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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
