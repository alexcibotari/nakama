package com.alexcibotari.nakama.web.rest.errors;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceError {

    private ZonedDateTime timestapm;
    private Integer status;
    private String path;

    private String message;
    private String description;

    private List<ResourceFieldError> fields;

    public ResourceError(String message) {
        this(message, null);
    }

    public ResourceError(String message, String description) {
        this(message, description, null);
    }

    public ResourceError(String message, String description, List<ResourceFieldError> fields) {
        this.timestapm = ZonedDateTime.now();
        this.message = message;
        this.description = description;
        this.fields = fields;
    }

    public void add(ResourceFieldError field) {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
        }
        this.fields.add(field);
    }

    public void add(Iterable<ResourceFieldError> fieldErrors) {
        fieldErrors.forEach(this::add);
    }

    public void add(ResourceFieldError... fieldErrors) {
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

    public List<ResourceFieldError> getFields() {
        return fields;
    }
}
