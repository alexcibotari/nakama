package com.alexcibotari.nakama.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"issue_id", "id_in_issue"}))
public class IssueWorklog extends AbstractAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false, updatable = false)
    private Issue issue;

    @Column(name = "id_in_issue", nullable = false, updatable = false)
    private Long idInIssue;

    @Column
    private String content;

    //In Minutes
    @Column(nullable = false)
    private Long timeWorked;

    @Column(nullable = false)
    private ZonedDateTime startDate;

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
        return "IssueWorklog{" +
            "issue=" + issue +
            ", idInIssue=" + idInIssue +
            ", content='" + content + '\'' +
            ", timeWorked=" + timeWorked +
            ", startDate=" + startDate +
            '}';
    }
}
