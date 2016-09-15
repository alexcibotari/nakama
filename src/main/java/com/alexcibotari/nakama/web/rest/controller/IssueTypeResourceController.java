package com.alexcibotari.nakama.web.rest.controller;

import com.alexcibotari.nakama.service.IssueTypeService;
import com.alexcibotari.nakama.web.rest.assembler.IssueTypeResourceAssembler;
import com.alexcibotari.nakama.web.rest.resource.IssueTypeResource;
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
@RequestMapping(path = "/api/issuetypes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(IssueTypeResource.class)
public class IssueTypeResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private IssueTypeService issueTypeService;

    @Autowired
    private IssueTypeResourceAssembler issueTypeResourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<IssueTypeResource>> issuePriorities() {
        Link link = entityLinks.linkToCollectionResource(IssueTypeResource.class);
        Resources<IssueTypeResource> resources = new Resources<>(issueTypeResourceAssembler.toResources(issueTypeService.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    public ResponseEntity<IssueTypeResource> issueType(@PathVariable Long id) {
        return ResponseEntity.ok(issueTypeResourceAssembler.toResource(issueTypeService.findOne(id)));
    }

    @PostMapping
    public ResponseEntity<IssueTypeResource> create(@RequestBody IssueTypeResource resource) {
        return new ResponseEntity<>(issueTypeResourceAssembler.toResource(issueTypeService.create(resource)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<IssueTypeResource> update(@PathVariable Long id, @RequestBody IssueTypeResource resource) {
        return ResponseEntity.ok(issueTypeResourceAssembler.toResource(issueTypeService.update(id, resource)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        issueTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
