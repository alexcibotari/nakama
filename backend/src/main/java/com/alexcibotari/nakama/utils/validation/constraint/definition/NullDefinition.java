package com.alexcibotari.nakama.utils.validation.constraint.definition;

import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
import javax.validation.constraints.Null;

public class NullDefinition extends ConstraintDefinition<NullDefinition, Null> {

  public NullDefinition(Null constraint) {
    this();
    message(constraint.message());
  }

  public NullDefinition() {
    super(Null.class);
  }
}
