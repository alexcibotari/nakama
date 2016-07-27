package com.alexcibotari.nakama.web.rest.constraint.definition;

import com.alexcibotari.nakama.web.rest.constraint.ConstraintDefinition;

import javax.validation.constraints.Digits;

public class DigitsDefinition extends ConstraintDefinition<DigitsDefinition, Digits> {

    public DigitsDefinition(Digits constraint) {
        this();
        message(constraint.message()).integer(constraint.integer()).fraction(constraint.fraction());
    }

    public DigitsDefinition() {
        super(Digits.class);
    }

    public DigitsDefinition integer(int integer) {
        addParameter("integer", integer);
        return this;
    }

    public DigitsDefinition fraction(int fraction) {
        addParameter("integer", fraction);
        return this;
    }
}
