package com.alexcibotari.nakama.web.rest.admin;

import com.alexcibotari.nakama.security.AuthoritiesConstants;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/admin/issuepriorities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class IssuePriorityResource {

    private final Logger log = LoggerFactory.getLogger(IssuePriorityResource.class);

    @Autowired
    private IssuePriorityService issuePriorityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<IssuePriorityDTO>> getAll() {
        return ResponseEntity.ok(issuePriorityService.findAll().stream().map(IssuePriorityDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<IssuePriorityDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(new IssuePriorityDTO(issuePriorityService.findOne(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<IssuePriorityDTO> create(@RequestBody IssuePriorityDTO dto) {
        return new ResponseEntity<>(new IssuePriorityDTO(issuePriorityService.create(dto)), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<IssuePriorityDTO> update(@RequestBody IssuePriorityDTO dto) {
        return ResponseEntity.ok(new IssuePriorityDTO(issuePriorityService.update(dto)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        issuePriorityService.delete(id);
        return ResponseEntity.ok().build();
    }

}
