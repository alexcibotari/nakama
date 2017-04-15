package com.alexcibotari.nakama.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql = "UPDATE Project SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Project extends AbstractAuditingEntity {

    @Column(name = "pname", nullable = false, unique = true)
    private String name;

    @Column(name = "pkey", nullable = false, unique = true)
    private String key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_user_id"/*, nullable = false*/)
    private User lead;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean deleted  = Boolean.FALSE;;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User getLead() {
        return lead;
    }

    public void setLead(User lead) {
        this.lead = lead;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Project{" +
            "name='" + name + '\'' +
            ", key='" + key + '\'' +
            ", lead=" + lead +
            ", description='" + description + '\'' +
            '}';
    }
}
