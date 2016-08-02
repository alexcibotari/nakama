package com.alexcibotari.nakama.domain;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"issue_id", "id_in_issue"}))
public class IssueComment extends AbstractAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "issue_id")
    Issue issue;

    @Column(name = "id_in_issue", nullable = false, updatable = false)
    private Long idInIssue;

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

    public Long getIdInIssue() {
        return idInIssue;
    }

    public void setIdInIssue(Long idInIssue) {
        this.idInIssue = idInIssue;
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
