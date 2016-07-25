package com.alexcibotari.nakama.web.rest.dto;


import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.domain.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {

    private String userName;

    private String email;

    private Boolean enabled;

    private Set<String> authorities;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getUserName(), user.getEmail(), user.getEnabled(), user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
    }

    public UserDTO(String userName, String email, Boolean enabled, Set<String> authorities) {
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
        return "UserDTO{" +
            "userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", enabled=" + enabled +
            ", authorities=" + authorities +
            '}';
    }
}
