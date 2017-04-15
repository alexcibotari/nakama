package com.alexcibotari.nakama.utils.validation.constraint.definition;

import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;

import javax.validation.constraints.Pattern;

public class PatternDefinition extends ConstraintDefinition<PatternDefinition, Pattern> {

    public PatternDefinition(Pattern constraint) {
        this();
        message(constraint.message()).regexp(constraint.regexp());
    }

    public PatternDefinition() {
        super(Pattern.class);
    }

    public PatternDefinition regexp(String regexp) {
        addParameter("regexp", regexp);
        return this;
    }

}
