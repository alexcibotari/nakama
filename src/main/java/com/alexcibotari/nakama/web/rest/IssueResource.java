package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.service.IssuePriorityService;
import com.alexcibotari.nakama.service.IssueService;
import com.alexcibotari.nakama.service.IssueStatusService;
import com.alexcibotari.nakama.service.IssueTypeService;
import com.alexcibotari.nakama.web.rest.dto.IssueDTO;
import com.alexcibotari.nakama.web.rest.dto.IssuePriorityDTO;
import com.alexcibotari.nakama.web.rest.dto.IssueStatusDTO;
import com.alexcibotari.nakama.web.rest.dto.IssueTypeDTO;
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

    @Autowired
    private IssuePriorityService issuePriorityService;

    @Autowired
    private IssueStatusService issueStatusService;

    @Autowired
    private IssueTypeService issueTypeService;

    @GetMapping(path = {"/issues/byproject/{projectKey}", "/projects/{projectKey}/issues"})
    public ResponseEntity<List<IssueDTO>> getIssuesByProjectKey(@PathVariable String projectKey) {
        List<IssueDTO> dtoList = issueService.findAllByProjectKey(projectKey).stream().map(IssueDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

/*    @RequestMapping(value = "/{id:[0-9]+}", method = RequestMethod.GET)
    public ResponseEntity<IssueDTO> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(new IssueDTO(issueService.findOne(id)), HttpStatus.OK);
    }*/

    @GetMapping(path = "/issues/{projectKey:[A-Z0-9]+}-{idInProject:[0-9]+}")
    public ResponseEntity<IssueDTO> getOne(@PathVariable String projectKey, @PathVariable Long idInProject) {
        return new ResponseEntity<>(new IssueDTO(issueService.findOne(projectKey, idInProject)), HttpStatus.OK);
    }


    @PostMapping(path = "/issues")
    public ResponseEntity<IssueDTO> create(@RequestBody IssueDTO dto) {
        return new ResponseEntity<>(new IssueDTO(issueService.create(dto)), HttpStatus.CREATED);
    }

    @PutMapping(path = "/issues")
    public ResponseEntity<IssueDTO> update(@RequestBody IssueDTO dto) {
        return new ResponseEntity<>(new IssueDTO(issueService.update(dto)), HttpStatus.OK);
    }

    @DeleteMapping(path = "/issues/{projectKey:[A-Z0-9]+}-{idInProject:[0-9]+}")
    public ResponseEntity<Void> delete(@PathVariable String projectKey, @PathVariable Long idInProject) {
        issueService.delete(projectKey, idInProject);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/issues/priority")
    public ResponseEntity<List<IssuePriorityDTO>> getAllPriority() {
        return ResponseEntity.ok(issuePriorityService.findAll().stream().map(IssuePriorityDTO::new).collect(Collectors.toList()));
    }

    @GetMapping(path = "/issues/status")
    public ResponseEntity<List<IssueStatusDTO>> getAllStatus() {
        return ResponseEntity.ok(issueStatusService.findAll().stream().map(IssueStatusDTO::new).collect(Collectors.toList()));
    }

    @GetMapping(path = "/issues/type")
    public ResponseEntity<List<IssueTypeDTO>> getAllType() {
        return ResponseEntity.ok(issueTypeService.findAll().stream().map(IssueTypeDTO::new).collect(Collectors.toList()));
    }

}
