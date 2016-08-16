package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.domain.IssueComment;
import com.alexcibotari.nakama.service.IssueCommentService;
import com.alexcibotari.nakama.web.rest.dto.IssueCommentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/issues/{projectKey:[A-Z0-9]+}-{idInProject:[0-9]+}/comments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

public class IssueCommentResource {

    private final Logger log = LoggerFactory.getLogger(IssueCommentResource.class);

    @Autowired
    private IssueCommentService issueCommentService;

    @GetMapping
    public ResponseEntity<List<IssueCommentDTO>> getCommentsByIssue(@PathVariable String projectKey, @PathVariable Long idInProject) {
        return ResponseEntity.ok(issueCommentService.findAllInIssue(projectKey, idInProject).stream().map(IssueCommentDTO::new).collect(Collectors.toList()));
    }

    @GetMapping(path = "/{idInIssue}")
    public ResponseEntity<IssueCommentDTO> getCommentById(@PathVariable String projectKey, @PathVariable Long idInProject, @PathVariable Long idInIssue) {
        IssueComment issueComment = issueCommentService.findOne(projectKey, idInProject, idInIssue);
        if (issueComment != null) {
            return ResponseEntity.ok(new IssueCommentDTO(issueComment));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<IssueCommentDTO> create(@PathVariable String projectKey, @PathVariable Long idInProject, @RequestBody IssueCommentDTO dto) {
        dto.setIssue(projectKey, idInProject);//Override DTO with path values
        return new ResponseEntity<>(new IssueCommentDTO(issueCommentService.create(dto)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<IssueCommentDTO> update(@PathVariable String projectKey, @PathVariable Long idInProject, @RequestBody IssueCommentDTO dto) {
        dto.setIssue(projectKey, idInProject);//Override DTO with path values
        return ResponseEntity.ok(new IssueCommentDTO(issueCommentService.update(dto)));
    }

    @DeleteMapping(path = "/{idInIssue}")
    public ResponseEntity<Void> delete(@PathVariable String projectKey, @PathVariable Long idInProject, @PathVariable Long idInIssue) {
        issueCommentService.delete(projectKey, idInProject, idInIssue);
        return ResponseEntity.ok().build();
    }
}
