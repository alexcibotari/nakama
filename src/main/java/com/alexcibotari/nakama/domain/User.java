package com.alexcibotari.nakama.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends AbstractAuditingEntity {

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "authorities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();


    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("UserName: ").append(getUserName()).append(", ");
        sb.append("Email: ").append(getEmail()).append(", ");
        sb.append("Enabled : ").append(getEnabled());
        sb.append("}");
        return sb.toString();
    }
}
