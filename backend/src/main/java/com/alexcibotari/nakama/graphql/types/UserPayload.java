package com.alexcibotari.nakama.graphql.types;

public class UserPayload extends PersonPayload {

  private String login;

  private String password;

  private Boolean enabled;

  @Override
  public String toString() {
    return "UserPayload{"
      + "login='" + login + '\''
      + ", password='" + password + '\''
      + ", enabled=" + enabled
      + '}';
  }
}
