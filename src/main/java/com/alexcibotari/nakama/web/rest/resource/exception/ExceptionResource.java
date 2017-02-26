package com.alexcibotari.nakama.web.rest.resource.exception;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExceptionResource {

    private ZonedDateTime timestapm;
    private Integer status;
    private String path;

    private String message;
    private String description;

    private List<FieldExceptionResource> fields;

    public ExceptionResource(String message) {
        this(message, null);
    }

    public ExceptionResource(String message, String description) {
        this(message, description, null);
    }

    public ExceptionResource(String message, String description, List<FieldExceptionResource> fields) {
        this.timestapm = ZonedDateTime.now();
        this.message = message;
        this.description = description;
        this.fields = fields;
    }

    public void add(FieldExceptionResource field) {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
        }
        this.fields.add(field);
    }

    public void add(Iterable<FieldExceptionResource> fieldErrors) {
        fieldErrors.forEach(this::add);
    }

    public void add(FieldExceptionResource... fieldErrors) {
        add(Arrays.asList(fieldErrors));
    }

    public ZonedDateTime getTimestapm() {
        return timestapm;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public List<FieldExceptionResource> getFields() {
        return fields;
    }
}
