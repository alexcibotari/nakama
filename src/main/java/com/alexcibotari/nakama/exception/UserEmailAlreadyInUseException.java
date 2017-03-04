package com.alexcibotari.nakama.exception;

public class UserEmailAlreadyInUseException extends AbstractServiceException {

    public final static String ERROR_ID = "user.email.alreadyInUse";

    public UserEmailAlreadyInUseException() {
        super(ERROR_ID);
    }

    public UserEmailAlreadyInUseException(String message) {
        super(ERROR_ID, message);
    }

    public UserEmailAlreadyInUseException(Throwable cause) {
        super(ERROR_ID, cause);
    }

    public UserEmailAlreadyInUseException(String message, Throwable cause) {
        super(ERROR_ID, message, cause);
    }
}
