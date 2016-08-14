package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueWorkLog;
import com.alexcibotari.nakama.service.util.key.KeyUtil;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class IssueWorkLogDTO extends AbstractAuditingDTO<Long> {

    private String issue;

    private String content;

    @NotNull
    @Min(1)
    private Long timeWorked;

    @NotNull
    private ZonedDateTime startDate;

    public IssueWorkLogDTO() {
    }

//    public IssueWorkLogDTO(Long id, String issue, String content, Long timeWorked, ZonedDateTime startDate) {
//        this.setId(id);
//        this.setIssue(issue);
//        this.setContent(content);
//        this.setTimeWorked(timeWorked);
//        this.setStartDate(startDate);
//    }

    public IssueWorkLogDTO(IssueWorkLog entity) {
        super(entity);
        this.setId(entity.getIdInIssue());//Override ID
        this.setIssue(entity.getIssue().getKey());
        this.setContent(entity.getContent());
        this.setTimeWorked(entity.getTimeWorked());
        this.setStartDate(entity.getStartDate());
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public void setIssue(String projectKey, Long idInProject) {
        this.issue = KeyUtil.getIssueKey(projectKey, idInProject);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(Long timeWorked) {
        this.timeWorked = timeWorked;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "IssueCommentDTO{" +
            "id=" + getId() +
            ", issue='" + issue + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
