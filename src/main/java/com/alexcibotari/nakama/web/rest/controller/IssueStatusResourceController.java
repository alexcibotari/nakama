package com.alexcibotari.nakama.web.rest.controller;

import com.alexcibotari.nakama.service.IssueStatusService;
import com.alexcibotari.nakama.web.rest.assembler.IssueStatusResourceAssembler;
import com.alexcibotari.nakama.web.rest.resource.IssueStatusResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/issuestatuses", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(IssueStatusResource.class)
public class IssueStatusResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private IssueStatusService issueStatusService;

    @Autowired
    private IssueStatusResourceAssembler issueStatusResourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<IssueStatusResource>> issuePriorities() {
        Link link = entityLinks.linkToCollectionResource(IssueStatusResource.class);
        Resources<IssueStatusResource> resources = new Resources<>(issueStatusResourceAssembler.toResources(issueStatusService.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    public ResponseEntity<IssueStatusResource> issueStatus(@PathVariable Long id) {
        return ResponseEntity.ok(issueStatusResourceAssembler.toResource(issueStatusService.findOne(id)));
    }

    @PostMapping
    public ResponseEntity<IssueStatusResource> create(@RequestBody IssueStatusResource resource) {
        return new ResponseEntity<>(issueStatusResourceAssembler.toResource(issueStatusService.create(resource)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<IssueStatusResource> update(@PathVariable Long id, @RequestBody IssueStatusResource resource) {
        return ResponseEntity.ok(issueStatusResourceAssembler.toResource(issueStatusService.update(id, resource)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        issueStatusService.delete(id);
        return ResponseEntity.ok().build();
    }

}
