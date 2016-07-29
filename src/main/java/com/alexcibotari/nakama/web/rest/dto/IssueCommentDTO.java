package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueComment;

public class IssueCommentDTO extends AbstractAuditingDTO {

    private Long id;

    private String issue;

    private String content;


    public IssueCommentDTO() {
    }

    public IssueCommentDTO(Long id, String issue, String content) {
        this.setId(id);
        this.setIssue(issue);
        this.setContent(content);
    }

    public IssueCommentDTO(IssueComment comment) {
        this(comment.getId(), comment.getIssue().getKey(), comment.getContent());
        this.setCreatedDate(comment.getCreatedDate());
        this.setLastModifiedDate(comment.getLastModifiedDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
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
            "id=" + id +
            ", issue='" + issue + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
