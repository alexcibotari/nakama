package com.alexcibotari.nakama.utils.validation.constraint.definition;

import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
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
