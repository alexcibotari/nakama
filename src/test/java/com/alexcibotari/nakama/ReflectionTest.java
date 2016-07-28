package com.alexcibotari.nakama;

import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.MaxDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.MinDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.NotNullDefinition;
import com.alexcibotari.nakama.utils.validation.constraint.definition.SizeDefinition;
import com.alexcibotari.nakama.web.rest.dto.UserDTO;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;

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


@WebAppConfiguration
@IntegrationTest
public class ReflectionTest {


    @Test
    public void getAnnotations() {

        Map<String, List<ConstraintDefinition>> params = new HashMap<>();
        System.out.println(UserDTO.class.getSimpleName());
        for (Field f : UserDTO.class.getDeclaredFields()) {
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

        System.out.println(params);
    }

}
