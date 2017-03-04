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

    @GetMapping(path = "project")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getProject() {
        return ResponseEntity.ok(Validator.extractConstraint(ProjectResource.class));
    }

    @GetMapping(path = "issue")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssue() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueResource.class));
    }

    @GetMapping(path = "issue/comment")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueComment() {
        return ResponseEntity.ok(Validator.extractConstraint(CommentResource.class));
    }

    @GetMapping(path = "issue/worklog")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueWorklog() {
        return ResponseEntity.ok(Validator.extractConstraint(WorkLogResource.class));
    }

    @GetMapping(path = "issuepriority")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssuePriority() {
        return ResponseEntity.ok(Validator.extractConstraint(IssuePriorityResource.class));
    }

    @GetMapping(path = "issuestatus")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueStatus() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueStatusResource.class));
    }

    @GetMapping(path = "issuetype")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueType() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueTypeResource.class));
    }

    @GetMapping(path = "user")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getUser() {
        return ResponseEntity.ok(Validator.extractConstraint(UserResource.class));
    }
}
