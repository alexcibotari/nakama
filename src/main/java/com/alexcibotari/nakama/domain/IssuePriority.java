package com.alexcibotari.nakama.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class IssuePriority extends AbstractAuditingEntity {

    @Column(name = "pname", nullable = false, unique = true, length = 50)
    private String name;

    @Column
    private String description;

    @Column
    private Long sequence;


    public IssuePriority() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "IssuePriority{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", sequence=" + sequence +
            '}';
    }
}
