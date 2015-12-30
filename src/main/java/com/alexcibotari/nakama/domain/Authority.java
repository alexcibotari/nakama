package com.alexcibotari.nakama.domain;

import com.alexcibotari.nakama.domain.generic.Domain;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Authority extends Domain implements GrantedAuthority {

    /**
     *
     */
    private static final long serialVersionUID = 7190787175352450609L;

    @Column(nullable = false, length = 50)
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("Version: ").append(getVersion());
        sb.append("}");
        return sb.toString();
    }

    public String getAuthority() {
        return name;
    }
}
