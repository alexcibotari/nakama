package com.alexcibotari.nakama.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Category extends AbstractAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    private String name;

    private String description;

    private boolean enabled;

    @Override
    public String toString() {
        return null;
    }
}
