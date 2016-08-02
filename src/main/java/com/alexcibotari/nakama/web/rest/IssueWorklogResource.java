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

    @GetMapping(path = "/{id}")
    public ResponseEntity<IssueWorklogDTO> getWorklogById(@PathVariable String projectKey, @PathVariable Long idInProject, @PathVariable Long id) {
        return ResponseEntity.ok(new IssueWorklogDTO(issueWorklogService.findOne(id)));
    }

    @PostMapping
    public ResponseEntity<IssueWorklogDTO> create(@RequestBody IssueWorklogDTO dto) {
        return new ResponseEntity<>(new IssueWorklogDTO(issueWorklogService.create(dto)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<IssueWorklogDTO> update(@RequestBody IssueWorklogDTO dto) {
        return ResponseEntity.ok(new IssueWorklogDTO(issueWorklogService.update(dto)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String projectKey, @PathVariable Long idInProject, @PathVariable Long id) {
        issueWorklogService.delete(id);
        return ResponseEntity.ok().build();
    }
}
