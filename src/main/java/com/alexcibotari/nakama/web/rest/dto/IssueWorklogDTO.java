package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueWorklog;

public class IssueWorklogDTO extends AbstractAuditingDTO {

    private String issue;

    private String author;

    private String content;


    public IssueWorklogDTO() {
    }

    public IssueWorklogDTO(Long id, String issue, String author, String content) {
        this.setId(id);
        this.setIssue(issue);
        this.setAuthor(author);
        this.setContent(content);
    }

    public IssueWorklogDTO(IssueWorklog worklog) {
        this(worklog.getId(), worklog.getIssue().getKey(), worklog.getAuthor().getUserName(), worklog.getContent());
        this.setCreatedDate(worklog.getCreatedDate());
        this.setLastModifiedDate(worklog.getLastModifiedDate());
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
