package com.alexcibotari.nakama.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Authority implements GrantedAuthority, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7190787175352450609L;

    @Id
    @Column(nullable = false, length = 50)
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getAuthority() {
        return this.name;
    }
}
