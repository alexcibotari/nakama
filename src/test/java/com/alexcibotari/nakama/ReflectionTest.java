package com.alexcibotari.nakama;

import com.alexcibotari.nakama.utils.validation.Validator;
import com.alexcibotari.nakama.web.rest.dto.IssueCommentDTO;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;


@WebAppConfiguration
@IntegrationTest
public class ReflectionTest {


    @Test
    public void getAnnotations() {
        System.out.println(Validator.extractConstraint(IssueCommentDTO.class));
    }

}
