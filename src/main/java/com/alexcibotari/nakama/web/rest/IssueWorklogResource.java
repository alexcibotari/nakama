package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.service.IssueWorklogService;
import com.alexcibotari.nakama.web.rest.dto.IssueWorklogDTO;
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
@RequestMapping(path = "/api/issues/{projectKey:[A-Z0-9]+}-{idInProject:[0-9]+}/worklogs", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class IssueWorklogResource {

    private final Logger log = LoggerFactory.getLogger(IssueWorklogResource.class);

    @Autowired
    private IssueWorklogService issueWorklogService;

    @GetMapping
    public ResponseEntity<List<IssueWorklogDTO>> getWorklogsByIssue(@PathVariable String projectKey, @PathVariable Long idInProject) {
        return ResponseEntity.ok(issueWorklogService.findAllInIssue(projectKey, idInProject).stream().map(IssueWorklogDTO::new).collect(Collectors.toList()));
    }

    @GetMapping(path = "/{idInIssue}")
    public ResponseEntity<IssueWorklogDTO> getWorklogById(@PathVariable String projectKey, @PathVariable Long idInProject, @PathVariable Long idInIssue) {
        return ResponseEntity.ok(new IssueWorklogDTO(issueWorklogService.findOne(projectKey, idInProject, idInIssue)));
    }

    @PostMapping
    public ResponseEntity<IssueWorklogDTO> create(@PathVariable String projectKey, @PathVariable Long idInProject, @RequestBody IssueWorklogDTO dto) {
        dto.setIssue(projectKey, idInProject);
        return new ResponseEntity<>(new IssueWorklogDTO(issueWorklogService.create(dto)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<IssueWorklogDTO> update(@PathVariable String projectKey, @PathVariable Long idInProject, @RequestBody IssueWorklogDTO dto) {
        dto.setIssue(projectKey, idInProject);
        return ResponseEntity.ok(new IssueWorklogDTO(issueWorklogService.update(dto)));
    }

    @DeleteMapping(path = "/{idInIssue}")
    public ResponseEntity<Void> delete(@PathVariable String projectKey, @PathVariable Long idInProject, @PathVariable Long idInIssue) {
        issueWorklogService.delete(projectKey, idInProject, idInIssue);
        return ResponseEntity.ok().build();
    }
}
