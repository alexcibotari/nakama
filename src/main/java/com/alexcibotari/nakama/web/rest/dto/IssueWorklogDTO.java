package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueWorkLog;
import com.alexcibotari.nakama.service.util.key.KeyUtil;

public class IssueWorkLogDTO extends AbstractAuditingDTO {

    private String issue;

    private String content;


    public IssueWorkLogDTO() {
    }

    public IssueWorkLogDTO(Long id, String issue, String content) {
        this.setId(id);
        this.setIssue(issue);
        this.setContent(content);
    }

    public IssueWorkLogDTO(IssueWorkLog workLog) {
        this(workLog.getIdInIssue(), workLog.getIssue().getKey(), workLog.getContent());
        this.setCreatedDate(workLog.getCreatedDate());
        this.setLastModifiedDate(workLog.getLastModifiedDate());
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
