package com.alexcibotari.nakama.web.rest.constraint.definition;

import com.alexcibotari.nakama.web.rest.constraint.ConstraintDefinition;

import javax.validation.constraints.Max;

public class MaxDefinition extends ConstraintDefinition<MaxDefinition, Max> {

    public MaxDefinition(Max constraint) {
        this();
        message(constraint.message()).value(constraint.value());
    }

    public MaxDefinition() {
        super(Max.class);
    }

    public MaxDefinition value(long min) {
        addParameter("value", min);
        return this;
    }
}
