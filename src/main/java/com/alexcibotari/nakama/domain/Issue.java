package com.alexcibotari.nakama.domain;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"project_id", "pkey"}))
public class Issue extends AbstractAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", updatable = false, nullable = false)
    private Project project;

    @Column(name = "pkey", nullable = false, updatable = false)
    private Long key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_user_id")
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigne_user_id")
    private User assigne;

    @Column(nullable = false)
    private String summery;

    @Column(nullable = false)
    private String description;

    //In Minutes
    @Column
    private Long timespent;

    //In Minutes
    @Column
    private Long timeestimate;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getAssigne() {
        return assigne;
    }

    public void setAssigne(User assigne) {
        this.assigne = assigne;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTimespent() {
        return timespent;
    }

    public void setTimespent(Long timespent) {
        this.timespent = timespent;
    }

    public Long getTimeestimate() {
        return timeestimate;
    }

    public void setTimeestimate(Long timeestimate) {
        this.timeestimate = timeestimate;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "project=" + project +
                ", key=" + key +
                ", reporter=" + reporter +
                ", assigne=" + assigne +
                ", summery='" + summery + '\'' +
                ", description='" + description + '\'' +
                ", timespent=" + timespent +
                ", timeestimate=" + timeestimate +
                '}';
    }
}
