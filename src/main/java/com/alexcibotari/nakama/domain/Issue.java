package com.alexcibotari.nakama.domain;

import com.alexcibotari.nakama.service.util.key.KeyUtil;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@SQLDelete(sql = "UPDATE Issue SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"project_id", "id_in_project"}))
public class Issue extends AbstractAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false, updatable = false)
    private Project project;

    @Column(name = "id_in_project", nullable = false, updatable = false)
    private Long idInProject;

    @ManyToOne
    @JoinColumn(name = "reporter_user_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "assigne_user_id")
    private User assigne;

    @Column(nullable = false)
    private String summery;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id", nullable = false)
    private IssuePriority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private IssueStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private IssueType type;

    //In Minutes
    @Column
    private Long timeSpent;

    //In Minutes
    @Column
    private Long timeEstimate;

    @Column
    private ZonedDateTime dueDate;

    @Column(nullable = false)
    private Boolean deleted = Boolean.FALSE;


    public String getKey() {
        return KeyUtil.getIssueKey(getProject().getKey(), getIdInProject());
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

    public Long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Long getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(Long timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
            ", priority=" + priority +
            ", status=" + status +
            ", type=" + type +
            ", timespent=" + timeSpent +
            ", timeestimate=" + timeEstimate +
            '}';
    }
}
