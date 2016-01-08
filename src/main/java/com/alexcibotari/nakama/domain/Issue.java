package com.alexcibotari.nakama.domain;

import javax.persistence.*;

@Entity
public class Issue extends AbstractAuditingEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", updatable = false, nullable = false)
    private Project project;

    @Column(name = "pkey", nullable = false, unique = true, updatable = false)
    private String key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigne_user_id")
    private User assigne;

    @Column(nullable = false)
    private String summery;

    @Column(nullable = false)
    private String description;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    @Override
    public String toString() {
        return "Issue{" +
                "project=" + project +
                ", key='" + key + '\'' +
                ", assigne=" + assigne +
                ", summery='" + summery + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
