package com.alexcibotari.nakama.web.rest.resource;


import org.springframework.hateoas.core.Relation;

import javax.validation.constraints.Size;
import java.util.Set;

@Relation(value = "user", collectionRelation = "users")
public class UserResource extends AbstractAuditingResource {

    @Size(min = 3, max = 50)
    private String userName;

    @Size(min = 5, max = 50)
    private String email;

    private Boolean enabled = Boolean.FALSE;

    private Set<String> authorities;

    public UserResource() {
    }

    public UserResource(String userName, String email, Boolean enabled, Set<String> authorities) {
        this.userName = userName;
        this.email = email;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "UserResource{" +
            "userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", enabled=" + enabled +
            ", authorities=" + authorities +
            '}';
    }
}
