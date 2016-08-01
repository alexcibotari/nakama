package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueComment;

import javax.validation.constraints.Size;

public class IssueCommentDTO extends AbstractAuditingDTO {

    private String issue;

    @Size(max = 250)
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
            "id='"+getId() + '\''+
            ", issue='" + issue + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
