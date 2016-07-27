package com.alexcibotari.nakama.web.rest.dto;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

public class TestDTO {

    @NotNull
    @NotEmpty
    private String name;

    @Size(min = 20, max = 30)
    private String description;


    @Min(4)
    @Max(55)
    @Digits(integer = 10, fraction = 2)
    private Long age;

    public TestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", age=" + age +
            '}';
    }
}
