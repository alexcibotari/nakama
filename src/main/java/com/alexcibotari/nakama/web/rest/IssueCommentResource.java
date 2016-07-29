package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.service.IssueCommentService;
import com.alexcibotari.nakama.web.rest.dto.IssueCommentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)

public class IssueCommentResource {

    private final Logger log = LoggerFactory.getLogger(IssueCommentResource.class);

    @Autowired
    private IssueCommentService issueCommentService;

    @RequestMapping(path = "/issues/{projectKey:[A-Z0-9]+}-{idInProject:[0-9]+}/comments", method = RequestMethod.GET)
    public ResponseEntity<List<IssueCommentDTO>> getCommentsByIssue(@PathVariable String projectKey, @PathVariable Long idInProject) {
        return ResponseEntity.ok(issueCommentService.findAllInIssue(projectKey, idInProject).stream().map(IssueCommentDTO::new).collect(Collectors.toList()));
    }
}
