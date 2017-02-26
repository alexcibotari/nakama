package com.alexcibotari.nakama.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Brand extends AbstractAuditingEntity {

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Override
    public String toString() {
        return null;
    }
}
