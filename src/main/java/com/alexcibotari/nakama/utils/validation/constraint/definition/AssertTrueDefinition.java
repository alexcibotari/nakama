package com.alexcibotari.nakama.utils.validation.constraint.definition;

import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;

import javax.validation.constraints.AssertTrue;

public class AssertTrueDefinition extends ConstraintDefinition<AssertTrueDefinition, AssertTrue> {

    public AssertTrueDefinition(AssertTrue constraint) {
        this();
        message(constraint.message());
    }

    public AssertTrueDefinition() {
        super(AssertTrue.class);
    }
}
