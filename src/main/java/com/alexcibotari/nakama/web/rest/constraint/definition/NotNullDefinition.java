package com.alexcibotari.nakama.web.rest.constraint.definition;

import com.alexcibotari.nakama.web.rest.constraint.ConstraintDefinition;

import javax.validation.constraints.NotNull;

public class NotNullDefinition extends ConstraintDefinition<NotNullDefinition, NotNull> {

    public NotNullDefinition(NotNull constraint) {
        this();
        message(constraint.message());
    }

    public NotNullDefinition() {
        super(NotNull.class);
    }
}
