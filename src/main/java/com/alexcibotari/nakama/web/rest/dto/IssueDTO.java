package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.Issue;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

public class IssueDTO extends AbstractAuditingDTO<String> {

    private Long idInProject;

    private String project;

    @Size(min = 5, max = 100)
    private String summery;

    @Size(max = 250)
    private String description;

    @NotNull
    private IssuePriorityDTO priority;

    @NotNull
    private IssueStatusDTO status;

    @NotNull
    private IssueTypeDTO type;

    private Long timeSpent;

    @Min(0)
    private Long timeEstimate;

    private ZonedDateTime dueDate;

    public IssueDTO() {
    }

//    public IssueDTO(Long idInProject, String project, String summery, String description) {
//        this.setKey(KeyUtil.getIssueKey(project, idInProject));
//        this.setIdInProject(idInProject);
//        this.setProject(project);
//        this.setSummery(summery);
//        this.setDescription(description);
//    }

    public IssueDTO(Issue entity) {
        super(entity);
        this.setId(entity.getKey());//Override Id with generated Key
        this.setIdInProject(entity.getIdInProject());
        this.setProject(entity.getProject().getKey());
        this.setSummery(entity.getSummery());
        this.setDescription(entity.getDescription());
        this.setPriority(new IssuePriorityDTO(entity.getPriority()));
        this.setStatus(new IssueStatusDTO(entity.getStatus()));
        this.setType(new IssueTypeDTO(entity.getType()));
        this.setTimeSpent(entity.getTimeSpent());
        this.setTimeEstimate(entity.getTimeEstimate());
        this.setDueDate(entity.getDueDate());
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

    public IssuePriorityDTO getPriority() {
        return priority;
    }

    public void setPriority(IssuePriorityDTO priority) {
        this.priority = priority;
    }

    public IssueStatusDTO getStatus() {
        return status;
    }

    public void setStatus(IssueStatusDTO status) {
        this.status = status;
    }

    public IssueTypeDTO getType() {
        return type;
    }

    public void setType(IssueTypeDTO type) {
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
        return "IssueDTO{" +
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
