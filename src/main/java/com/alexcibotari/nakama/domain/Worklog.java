package com.alexcibotari.nakama.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Worklog extends AbstractAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false, updatable = false)
    private Issue issue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_user_id", nullable = false, updatable = false)
    private User author;

    @Column
    private String body;

    //In Minutes
    @Column(name = "timewrked", nullable = false)
    private Long timeWorked;

    @Column(nullable = false)
    private ZonedDateTime startDate;

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
        return "Worklog{" +
                "issue=" + issue +
                ", body='" + body + '\'' +
                ", timeWorked=" + timeWorked +
                ", startDate=" + startDate +
                '}';
    }
}
