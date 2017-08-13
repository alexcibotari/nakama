package com.alexcibotari.nakama.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends AbstractAuditingEntity {

    @Column(name = "pname", nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private Personal personal;

    @Column(name = "password_hash", nullable = false, length = 255)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "authorities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private Set<Authority> authorities = new HashSet<>();


    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", personal=" + personal +
            ", password='" + password + '\'' +
            ", enabled=" + enabled +
            ", authorities=" + authorities +
            '}';
    }
}
