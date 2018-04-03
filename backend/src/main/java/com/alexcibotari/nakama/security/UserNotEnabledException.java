package com.alexcibotari.nakama.security;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is throw in case of a not activated user trying to authenticate.
 */
public class UserNotEnabledException extends AuthenticationException {

  public UserNotEnabledException(String message) {
    super(message);
  }

  public UserNotEnabledException(String message, Throwable t) {
    super(message, t);
  }
}
