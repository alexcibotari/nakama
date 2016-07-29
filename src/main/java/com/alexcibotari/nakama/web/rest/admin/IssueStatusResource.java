package com.alexcibotari.nakama.web.rest.admin;

import com.alexcibotari.nakama.security.AuthoritiesConstants;
import com.alexcibotari.nakama.service.IssueStatusService;
import com.alexcibotari.nakama.web.rest.dto.IssueStatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/admin/issues/statuses", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class IssueStatusResource {

    private final Logger log = LoggerFactory.getLogger(IssueStatusResource.class);

    @Autowired
    private IssueStatusService issueStatusService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<IssueStatusDTO>> getAll() {
        return ResponseEntity.ok(issueStatusService.findAll().stream().map(IssueStatusDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<IssueStatusDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(new IssueStatusDTO(issueStatusService.findOne(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<IssueStatusDTO> create(@RequestBody IssueStatusDTO dto) {
        return new ResponseEntity<>(new IssueStatusDTO(issueStatusService.create(dto)), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<IssueStatusDTO> update(@RequestBody IssueStatusDTO dto) {
        return ResponseEntity.ok(new IssueStatusDTO(issueStatusService.update(dto)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        issueStatusService.delete(id);
        return ResponseEntity.ok().build();
    }

}
