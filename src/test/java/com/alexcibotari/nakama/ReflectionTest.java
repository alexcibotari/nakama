package com.alexcibotari.nakama;

import com.alexcibotari.nakama.utils.validation.Validator;
import com.alexcibotari.nakama.web.rest.resource.CommentResource;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;


@WebAppConfiguration
@SpringBootTest
public class ReflectionTest {


    @Test
    public void getAnnotations() {
        System.out.println(Validator.extractConstraint(CommentResource.class));
    }

}
