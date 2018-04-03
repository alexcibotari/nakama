package com.alexcibotari.nakama.exception;

import java.util.UUID;

public abstract class AbstractServiceException extends RuntimeException {

  private String errorId;
  private String uuid;
  private long timestamp;


  {
    UUID tmp = UUID.randomUUID();
    this.uuid = tmp.toString();
    this.timestamp = tmp.timestamp();

  }

  public AbstractServiceException(String errorId) {
    this.errorId = errorId;
  }

  public AbstractServiceException(String errorId, String message) {
    super(message);
    this.errorId = errorId;
  }

  public AbstractServiceException(String errorId, Throwable cause) {
    super(cause);
    this.errorId = errorId;
  }

  public AbstractServiceException(String errorId, String message, Throwable cause) {
    super(message, cause);
    this.errorId = errorId;
  }

  public String getErrorId() {
    return errorId;
  }

  public String getUuid() {
    return uuid;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
