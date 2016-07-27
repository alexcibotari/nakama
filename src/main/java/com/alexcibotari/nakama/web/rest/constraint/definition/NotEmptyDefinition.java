package com.alexcibotari.nakama.web.rest.constraint.definition;

import com.alexcibotari.nakama.web.rest.constraint.ConstraintDefinition;
import org.hibernate.validator.constraints.NotEmpty;

public class NotEmptyDefinition extends ConstraintDefinition<NotEmptyDefinition, NotEmpty> {

    public NotEmptyDefinition(NotEmpty constraint){
        this();
        message(constraint.message());
    }
    public NotEmptyDefinition() {
        super(NotEmpty.class);
    }
}
