package com.alexcibotari.nakama.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends Person {

    @Column(nullable = false, unique = true, updatable = false)
    private String login;

    @Column(name = "password_hash", nullable = false)
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
            "login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", enabled=" + enabled +
            ", authorities=" + authorities +
            '}';
    }
}
