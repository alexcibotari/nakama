package com.alexcibotari.nakama.web.rest.resource;

import org.springframework.hateoas.core.Relation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Relation(value = "issue", collectionRelation = "issues")
public class IssueResource extends AbstractAuditingResource{

    private Long idInProject;

    private String project;

    @Size(min = 5, max = 100)
    private String summery;

    @Size(max = 250)
    private String description;

    @NotNull
    private IssuePriorityResource priority;

    @NotNull
    private IssueStatusResource status;

    @NotNull
    private IssueTypeResource type;

    private Long timeSpent;

    @Min(0)
    private Long timeEstimate;

    private ZonedDateTime dueDate;

    public IssueResource() {
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

    public IssuePriorityResource getPriority() {
        return priority;
    }

    public void setPriority(IssuePriorityResource priority) {
        this.priority = priority;
    }

    public IssueStatusResource getStatus() {
        return status;
    }

    public void setStatus(IssueStatusResource status) {
        this.status = status;
    }

    public IssueTypeResource getType() {
        return type;
    }

    public void setType(IssueTypeResource type) {
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

    @Override
    public String toString() {
        return "IssueResource{" +
            "idInProject=" + idInProject +
            ", project='" + project + '\'' +
            ", summery='" + summery + '\'' +
            ", description='" + description + '\'' +
            ", priority=" + priority +
            ", status=" + status +
            ", type=" + type +
            ", timeSpent=" + timeSpent +
            ", timeEstimate=" + timeEstimate +
            ", dueDate=" + dueDate +
            '}';
    }
}
