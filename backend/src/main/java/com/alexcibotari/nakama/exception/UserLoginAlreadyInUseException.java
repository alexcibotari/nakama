package com.alexcibotari.nakama.exception;

public class UserLoginAlreadyInUseException extends AbstractServiceException {

  public static final String ERROR_ID = "user.login.alreadyInUse";

  public UserLoginAlreadyInUseException() {
    super(ERROR_ID);
  }

  public UserLoginAlreadyInUseException(String message) {
    super(ERROR_ID, message);
  }

  public UserLoginAlreadyInUseException(Throwable cause) {
    super(ERROR_ID, cause);
  }

  public UserLoginAlreadyInUseException(String message, Throwable cause) {
    super(ERROR_ID, message, cause);
  }
}
