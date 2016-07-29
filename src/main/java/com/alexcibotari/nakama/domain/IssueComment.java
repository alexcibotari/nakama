package com.alexcibotari.nakama.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IssueComment extends AbstractAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "issue_id")
    Issue issue;

    @Column
    private String content;

    public IssueComment() {
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
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
        return "IssueComment{" +
            "issue=" + issue +
            ", content='" + content + '\'' +
            '}';
    }
}
