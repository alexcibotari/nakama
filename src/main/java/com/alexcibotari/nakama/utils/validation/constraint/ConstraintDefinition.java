package com.alexcibotari.nakama.utils.validation.constraint;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public abstract class ConstraintDefinition<C extends ConstraintDefinition<C, A>, A extends Annotation> {
    private String constraintType;
    private Map<String, Object> parameters;

    public ConstraintDefinition(Class<A> constraintType) {
        this.constraintType = constraintType.getSimpleName();
        this.parameters = new HashMap<>();
    }

    private C getThis() {
        return (C) this;
    }

    protected void addParameter(String key, Object value) {
        parameters.put(key, value);
    }

    public C message(String message) {
        addParameter("message", message);
        return getThis();
    }

    public String getConstraintType() {
        return constraintType;
    }


    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "ConstraintDefinition{" +
            "constraintType='" + constraintType + '\'' +
            ", parameters=" + parameters +
            '}';
    }
}
