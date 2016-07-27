package com.alexcibotari.nakama.web.rest.constraint;

import com.alexcibotari.nakama.web.rest.constraint.definition.MaxDefinition;
import com.alexcibotari.nakama.web.rest.constraint.definition.MinDefinition;
import com.alexcibotari.nakama.web.rest.constraint.definition.NotNullDefinition;
import com.alexcibotari.nakama.web.rest.constraint.definition.SizeDefinition;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static Map<String, List<ConstraintDefinition>> extractRule(Class clazz) {
        Map<String, List<ConstraintDefinition>> params = new HashMap<>();
        for (Field f : clazz.getDeclaredFields()) {
            List<ConstraintDefinition> definitionList = new ArrayList<>();
            for (Annotation annotation : f.getAnnotations()) {
                if (annotation instanceof NotNull) {
                    definitionList.add(new NotNullDefinition((NotNull) annotation));
                } else if (annotation instanceof Max) {
                    definitionList.add(new MaxDefinition((Max) annotation));
                } else if (annotation instanceof Min) {
                    definitionList.add(new MinDefinition((Min) annotation));
                } else if (annotation instanceof Size) {
                    definitionList.add(new SizeDefinition((Size) annotation));
                }
            }
            params.put(f.getName(), definitionList);
        }
        return params;
    }
}
