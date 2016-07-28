package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.utils.validation.Validator;
import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
import com.alexcibotari.nakama.web.rest.dto.TestDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/constraints", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ConstraintResource {

    @RequestMapping(path = "test", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getTest() {
        return ResponseEntity.ok(Validator.extractConstraint(TestDTO.class));
    }
}
