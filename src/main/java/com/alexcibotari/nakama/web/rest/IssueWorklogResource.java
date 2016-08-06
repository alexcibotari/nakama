package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.service.IssueWorkLogService;
import com.alexcibotari.nakama.web.rest.dto.IssueWorkLogDTO;
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
    private IssueWorkLogService issueWorkLogService;

    @GetMapping
    public ResponseEntity<List<IssueWorkLogDTO>> getWorklogsByIssue(@PathVariable String projectKey, @PathVariable Long idInProject) {
        return ResponseEntity.ok(issueWorkLogService.findAllInIssue(projectKey, idInProject).stream().map(IssueWorkLogDTO::new).collect(Collectors.toList()));
    }

    @GetMapping(path = "/{idInIssue}")
    public ResponseEntity<IssueWorkLogDTO> getWorklogById(@PathVariable String projectKey, @PathVariable Long idInProject, @PathVariable Long idInIssue) {
        return ResponseEntity.ok(new IssueWorkLogDTO(issueWorkLogService.findOne(projectKey, idInProject, idInIssue)));
    }

    @PostMapping
    public ResponseEntity<IssueWorkLogDTO> create(@PathVariable String projectKey, @PathVariable Long idInProject, @RequestBody IssueWorkLogDTO dto) {
        dto.setIssue(projectKey, idInProject);
        return new ResponseEntity<>(new IssueWorkLogDTO(issueWorkLogService.create(dto)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<IssueWorkLogDTO> update(@PathVariable String projectKey, @PathVariable Long idInProject, @RequestBody IssueWorkLogDTO dto) {
        dto.setIssue(projectKey, idInProject);
        return ResponseEntity.ok(new IssueWorkLogDTO(issueWorkLogService.update(dto)));
    }

    @DeleteMapping(path = "/{idInIssue}")
    public ResponseEntity<Void> delete(@PathVariable String projectKey, @PathVariable Long idInProject, @PathVariable Long idInIssue) {
        issueWorkLogService.delete(projectKey, idInProject, idInIssue);
        return ResponseEntity.ok().build();
    }
}
