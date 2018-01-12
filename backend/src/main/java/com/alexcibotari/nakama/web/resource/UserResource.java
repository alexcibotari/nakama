package com.alexcibotari.nakama.web.resource;

import javax.validation.constraints.Size;
import java.util.Set;

public class UserResource extends PersonResource {

    @Size(min = 3, max = 50)
    private String login;

    private Boolean enabled = Boolean.FALSE;

    private Set<AuthorityResource> authorities;

    public UserResource() {
    }

    public UserResource(String login, Boolean enabled, Set<AuthorityResource> authorities) {
        this.login = login;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<AuthorityResource> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityResource> authorities) {
        this.authorities = authorities;
    }

    public String toString() {
        return "UserResource{" +
            "login='" + login + '\'' +
            ", enabled=" + enabled +
            ", authorities=" + authorities +
            '}';
    }
}
