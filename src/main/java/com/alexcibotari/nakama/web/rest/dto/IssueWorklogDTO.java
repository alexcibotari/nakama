package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueWorklog;
import com.alexcibotari.nakama.service.util.key.KeyUtil;

public class IssueWorklogDTO extends AbstractAuditingDTO {

    private String issue;

    private String content;


    public IssueWorklogDTO() {
    }

    public IssueWorklogDTO(Long id, String issue, String content) {
        this.setId(id);
        this.setIssue(issue);
        this.setContent(content);
    }

    public IssueWorklogDTO(IssueWorklog worklog) {
        this(worklog.getIdInIssue(), worklog.getIssue().getKey(), worklog.getContent());
        this.setCreatedDate(worklog.getCreatedDate());
        this.setLastModifiedDate(worklog.getLastModifiedDate());
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

    @Override
    public String toString() {
        return "IssueCommentDTO{" +
            "id=" + getId() +
            ", issue='" + issue + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
