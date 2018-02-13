package com.alexcibotari.nakama.utils.validation.constraint.definition;

import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
import javax.validation.constraints.Min;

public class MinDefinition extends ConstraintDefinition<MinDefinition, Min> {

  public MinDefinition(Min constraint) {
    this();
    message(constraint.message()).value(constraint.value());
  }

  public MinDefinition() {
    super(Min.class);
  }

  public MinDefinition value(long min) {
    addParameter("value", min);
    return this;
  }
}
