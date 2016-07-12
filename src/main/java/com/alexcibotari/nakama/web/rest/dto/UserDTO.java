package com.alexcibotari.nakama.web.rest.dto;


import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.domain.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {

    private String userName;

    private String email;

    private Set<String> authorities;


    public UserDTO(User user) {
        this(user.getUserName(), user.getEmail(), user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
    }

    public UserDTO(String userName, String email, Set<String> authorities) {
        this.userName = userName;
        this.email = email;
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
                ", authorities=" + authorities +
                '}';
    }
}
