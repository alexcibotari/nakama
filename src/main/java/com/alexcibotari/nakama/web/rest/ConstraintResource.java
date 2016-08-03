package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.utils.validation.Validator;
import com.alexcibotari.nakama.utils.validation.constraint.ConstraintDefinition;
import com.alexcibotari.nakama.web.rest.dto.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/constraints", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ConstraintResource {

    @GetMapping(path = "test")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getTest() {
        return ResponseEntity.ok(Validator.extractConstraint(TestDTO.class));
    }

    @GetMapping(path = "project")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getProject() {
        return ResponseEntity.ok(Validator.extractConstraint(ProjectDTO.class));
    }

    @GetMapping(path = "issue")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssue() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueDTO.class));
    }

    @GetMapping(path = "issue/comment")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueComment() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueCommentDTO.class));
    }

    @GetMapping(path = "issue/worklog")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueWorklog() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueWorklogDTO.class));
    }

    @GetMapping(path = "issue/priority")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssuePriority() {
        return ResponseEntity.ok(Validator.extractConstraint(IssuePriorityDTO.class));
    }

    @GetMapping(path = "issue/status")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueStatus() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueStatusDTO.class));
    }

    @GetMapping(path = "issue/type")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getIssueType() {
        return ResponseEntity.ok(Validator.extractConstraint(IssueTypeDTO.class));
    }

    @GetMapping(path = "user")
    public ResponseEntity<Map<String, List<ConstraintDefinition>>> getUser() {
        return ResponseEntity.ok(Validator.extractConstraint(UserDTO.class));
    }
}
