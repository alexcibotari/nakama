package com.alexcibotari.nakama.utils.validation.constraint.definition;

import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
import javax.validation.constraints.DecimalMax;

public class DecimalMaxDefinition extends ConstraintDefinition<DecimalMaxDefinition, DecimalMax> {

  public DecimalMaxDefinition(DecimalMax constraint) {
    this();
    message(constraint.message()).value(constraint.value()).inclusive(constraint.inclusive());
  }

  public DecimalMaxDefinition() {
    super(DecimalMax.class);
  }

  public DecimalMaxDefinition value(String value) {
    addParameter("value", value);
    return this;
  }

  public DecimalMaxDefinition inclusive(boolean inclusive) {
    addParameter("inclusive", inclusive);
    return this;
  }
}
