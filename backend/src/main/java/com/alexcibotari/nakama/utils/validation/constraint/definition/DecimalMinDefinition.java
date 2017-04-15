package com.alexcibotari.nakama.utils.validation.constraint.definition;

import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;

import javax.validation.constraints.DecimalMin;

public class DecimalMinDefinition extends ConstraintDefinition<DecimalMinDefinition, DecimalMin> {

    public DecimalMinDefinition(DecimalMin constraint) {
        this();
        message(constraint.message()).value(constraint.value()).inclusive(constraint.inclusive());
    }

    public DecimalMinDefinition() {
        super(DecimalMin.class);
    }

    public DecimalMinDefinition value(String value) {
        addParameter("value", value);
        return this;
    }

    public DecimalMinDefinition inclusive(boolean inclusive) {
        addParameter("inclusive", inclusive);
        return this;
    }
}
