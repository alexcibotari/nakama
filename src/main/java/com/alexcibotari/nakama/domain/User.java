package com.alexcibotari.nakama.domain;

import com.alexcibotari.nakama.domain.generic.IdDomain;
import com.alexcibotari.nakama.util.DomainUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable, IdDomain {

    @Id
    private Long id;

    @Version
    private int version;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, length = 255)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "authorities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("UserName: ").append(getUserName()).append(", ");
        sb.append("Authorities Ids: ").append(DomainUtil.getIds(getAuthorities())).append(", ");
        sb.append("Enabled : ").append(getEnabled()).append(", ");
        sb.append("Version: ").append(getVersion());
        sb.append("}");
        return sb.toString();
    }
}
