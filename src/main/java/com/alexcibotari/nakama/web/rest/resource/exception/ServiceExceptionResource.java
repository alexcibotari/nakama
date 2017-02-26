package com.alexcibotari.nakama.web.rest.resource.exception;

import com.alexcibotari.nakama.exception.AbstractServiceException;

public class ServiceExceptionResource {

    private String errorId;
    private String uuid;
    private long timestamp;


    public ServiceExceptionResource(AbstractServiceException ex) {
        this.errorId = ex.getErrorId();
        this.uuid = ex.getUuid();
        this.timestamp = ex.getTimestamp();
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

    @Override
    public String toString() {
        return "ServiceExceptionResource{" +
            "errorId='" + errorId + '\'' +
            ", uuid='" + uuid + '\'' +
            ", timestamp=" + timestamp +
            '}';
    }
}
