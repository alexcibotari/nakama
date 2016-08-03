package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueComment;
import com.alexcibotari.nakama.service.util.key.KeyUtil;

import javax.validation.constraints.Size;

public class IssueCommentDTO extends AbstractAuditingDTO {

    private String issue;

    @Size(min = 3, max = 250)
    private String content;


    public IssueCommentDTO() {
    }

    public IssueCommentDTO(Long id, String issue, String content) {
        this.setId(id);
        this.setIssue(issue);
        this.setContent(content);
    }

    public IssueCommentDTO(IssueComment comment) {
        this(comment.getIdInIssue(), comment.getIssue().getKey(), comment.getContent());
        this.setCreatedDate(comment.getCreatedDate());
        this.setLastModifiedDate(comment.getLastModifiedDate());
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
            "id='" + getId() + '\'' +
            ", issue='" + issue + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
