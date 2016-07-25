package com.alexcibotari.nakama.domain;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"project_id", "id_in_project"}))
public class Issue extends AbstractAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false, updatable = false)
    private Project project;

    @Column(name = "id_in_project", nullable = false, updatable = false)
    private Long idInProject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_user_id")
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigne_user_id")
    private User assigne;

    @Column(nullable = false)
    private String summery;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "priority_id", nullable = false)
    private IssuePriority priority;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private IssueStatus status;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private IssueType type;

    //In Minutes
    @Column
    private Long timespent;

    //In Minutes
    @Column
    private Long timeestimate;


    public String getKey() {
        return getProject().getKey() + "-" + getIdInProject();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getIdInProject() {
        return idInProject;
    }

    public void setIdInProject(Long idInProject) {
        this.idInProject = idInProject;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getAssigne() {
        return assigne;
    }

    public void setAssigne(User assigne) {
        this.assigne = assigne;
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

    public IssuePriority getPriority() {
        return priority;
    }

    public void setPriority(IssuePriority priority) {
        this.priority = priority;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public IssueType getType() {
        return type;
    }

    public void setType(IssueType type) {
        this.type = type;
    }

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }

    public Long getTimeestimate() {
        return timeestimate;
    }

    public void setTimeestimate(Long timeestimate) {
        this.timeestimate = timeestimate;
    }

    @Override
    public String toString() {
        return "Issue{" +
            "project=" + project +
            ", idInProject=" + idInProject +
            ", reporter=" + reporter +
            ", assigne=" + assigne +
            ", summery='" + summery + '\'' +
            ", description='" + description + '\'' +
            ", timespent=" + timespent +
            ", timeestimate=" + timeestimate +
            '}';
    }
}
