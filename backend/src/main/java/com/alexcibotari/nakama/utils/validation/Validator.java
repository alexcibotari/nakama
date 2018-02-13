package com.alexcibotari.nakama.utils.validation;

import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.AssertFalseDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.AssertTrueDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.DecimalMaxDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.DecimalMinDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.DigitsDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.FutureDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.MaxDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.MinDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.NotNullDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.NullDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.PastDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.PatternDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.SizeDefinition;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Validator {

  public static Map<String, List<ConstraintDefinition>> extractConstraint(Class clazz) {
    Map<String, List<ConstraintDefinition>> params = new HashMap<>();
    Class<?> currentClass = clazz;
    while (currentClass.getSuperclass() != null) {
      params = getClassConstraints(params, currentClass);
      currentClass = currentClass.getSuperclass();
    }
    return params;
  }

  private static Map<String, List<ConstraintDefinition>> getClassConstraints(
    Map<String, List<ConstraintDefinition>> params, Class clazz) {
    for (Field f : clazz.getDeclaredFields()) {
      List<ConstraintDefinition> definitionList = new ArrayList<>();
      for (Annotation annotation : f.getAnnotations()) {
        if (annotation instanceof AssertFalse) {
          definitionList.add(new AssertFalseDefinition(((AssertFalse) annotation)));
        } else if (annotation instanceof AssertTrue) {
          definitionList.add(new AssertTrueDefinition(((AssertTrue) annotation)));
        } else if (annotation instanceof DecimalMax) {
          definitionList.add(new DecimalMaxDefinition(((DecimalMax) annotation)));
        } else if (annotation instanceof DecimalMin) {
          definitionList.add(new DecimalMinDefinition(((DecimalMin) annotation)));
        } else if (annotation instanceof Digits) {
          definitionList.add(new DigitsDefinition(((Digits) annotation)));
        } else if (annotation instanceof Future) {
          definitionList.add(new FutureDefinition(((Future) annotation)));
        } else if (annotation instanceof Max) {
          definitionList.add(new MaxDefinition((Max) annotation));
        } else if (annotation instanceof Min) {
          definitionList.add(new MinDefinition((Min) annotation));
        } else if (annotation instanceof NotNull) {
          definitionList.add(new NotNullDefinition((NotNull) annotation));
        } else if (annotation instanceof Null) {
          definitionList.add(new NullDefinition((Null) annotation));
        } else if (annotation instanceof Past) {
          definitionList.add(new PastDefinition(((Past) annotation)));
        } else if (annotation instanceof Pattern) {
          definitionList.add(new PatternDefinition(((Pattern) annotation)));
        } else if (annotation instanceof Size) {
          definitionList.add(new SizeDefinition((Size) annotation));
        }
      }
      if (!definitionList.isEmpty()) {
        params.put(f.getName(), definitionList);
      }
    }
    return params;
  }
}
