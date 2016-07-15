package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.service.IssueService;
import com.alexcibotari.nakama.web.rest.dto.IssueDTO;
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
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class IssueResource {

    private final Logger log = LoggerFactory.getLogger(IssueResource.class);

    @Autowired
    private IssueService issueService;

    @RequestMapping(path = {"/issues/byproject/{projectKey}", "/projects/{projectKey}/issues"}, method = RequestMethod.GET)
    public ResponseEntity<List<IssueDTO>> getIssuesByProjectKey(@PathVariable String projectKey) {
        List<IssueDTO> dtoList = issueService.findAllByProjectKey(projectKey).stream().map(IssueDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

/*    @RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.GET)
    public ResponseEntity<IssueDTO> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(new IssueDTO(issueService.findOne(id)), HttpStatus.OK);
    }*/

    @RequestMapping(path = "/issues/{projectKey:[A-Z0-9]+}-{idInProject:[0-9]+}", method = RequestMethod.GET)
    public ResponseEntity<IssueDTO> getOne(@PathVariable String projectKey, @PathVariable Long idInProject) {
        return new ResponseEntity<>(new IssueDTO(issueService.findOne(projectKey, idInProject)), HttpStatus.OK);
    }


    @RequestMapping(path = "/issues", method = RequestMethod.POST)
    public ResponseEntity<IssueDTO> create(@RequestBody IssueDTO dto) {
        return new ResponseEntity<>(new IssueDTO(issueService.create(dto)), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/issues", method = RequestMethod.PUT)
    public ResponseEntity<IssueDTO> update(@RequestBody IssueDTO dto) {
        return new ResponseEntity<>(new IssueDTO(issueService.update(dto)), HttpStatus.OK);
    }

    @RequestMapping(path = "/issues/{projectKey:[A-Z0-9]+}-{idInProject:[0-9]+}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String projectKey, @PathVariable Long idInProject) {
        issueService.delete(projectKey, idInProject);
        return ResponseEntity.ok().build();
    }

}
