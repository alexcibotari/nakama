package com.alexcibotari.nakama;

import com.alexcibotari.nakama.web.rest.dto.UserDTO;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


@WebAppConfiguration
@IntegrationTest
public class ReflectionTest {


    @Test
    public void getAnnotations() {
        Class<?> c = UserDTO.class;

        for (Field f: c.getDeclaredFields()) {
            System.out.println(f.getName());
            for(Annotation a : f.getAnnotations()){
                System.out.println("\t"+a.toString());
            }
        }
    }

}
