package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.utils.validation.Validator;
import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
import com.alexcibotari.nakama.web.rest.dto.*;
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

    @RequestMapping(path = "project", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getProject() {
        return ResponseEntity.ok(Validator.extractConstraint(ProjectDTO.class));
    }

    @RequestMapping(path = "issue", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssue() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueDTO.class));
    }

    @RequestMapping(path = "issue/comment", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueComment() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueCommentDTO.class));
    }

    @RequestMapping(path = "issue/priority", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssuePriority() {
        return ResponseEntity.ok(Validator.extractConstraint(IssuePriorityDTO.class));
    }

    @RequestMapping(path = "issue/status", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueStatus() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueStatusDTO.class));
    }

    @RequestMapping(path = "issue/type", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueType() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueTypeDTO.class));
    }

    @RequestMapping(path = "user", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getUser() {
        return ResponseEntity.ok(Validator.extractConstraint(UserDTO.class));
    }
}
