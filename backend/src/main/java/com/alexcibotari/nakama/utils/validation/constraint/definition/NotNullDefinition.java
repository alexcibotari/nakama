package com.alexcibotari.nakama.utils.validation.constraint.definition;

import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
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
