package com.alexcibotari.nakama.model;

import com.datastax.driver.core.DataType.Name;
import java.util.Set;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class User {

  @PrimaryKey
  private String login;
  private String email;

  private String password;

  private boolean enabled;

  @CassandraType(type = Name.SET, typeArguments = Name.TEXT)
  private Set<Authority> authorities;

  public User() {
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public String toString() {
    return "User{"
      + "login='" + login + '\''
      + ", email='" + email + '\''
      + ", password='" + password + '\''
      + ", enabled=" + enabled
      + ", authorities=" + authorities
      + '}';
  }
}
