package com.alexcibotari.nakama.web.controller;

import com.alexcibotari.nakama.utils.validation.Validator;
import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
import com.alexcibotari.nakama.web.resource.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/constraints", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ConstraintResourceController {

    @GetMapping(path = "test")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getTest() {
        return ResponseEntity.ok(Validator.extractConstraint(TestResource.class));
    }

    @GetMapping(path = "user")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getUser() {
        return ResponseEntity.ok(Validator.extractConstraint(UserResource.class));
    }
}
