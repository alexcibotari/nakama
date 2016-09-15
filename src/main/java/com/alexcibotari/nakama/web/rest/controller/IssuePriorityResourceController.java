package com.alexcibotari.nakama.web.rest.controller;

import com.alexcibotari.nakama.service.IssuePriorityService;
import com.alexcibotari.nakama.web.rest.assembler.IssuePriorityResourceAssembler;
import com.alexcibotari.nakama.web.rest.resource.IssuePriorityResource;
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
@RequestMapping(path = "/api/issuepriorities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(IssuePriorityResource.class)
public class IssuePriorityResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private IssuePriorityService issuePriorityService;

    @Autowired
    private IssuePriorityResourceAssembler issuePriorityResourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<IssuePriorityResource>> issuePriorities() {
        Link link = entityLinks.linkToCollectionResource(IssuePriorityResource.class);
        Resources<IssuePriorityResource> resources = new Resources<>(issuePriorityResourceAssembler.toResources(issuePriorityService.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    public ResponseEntity<IssuePriorityResource> issuePriority(@PathVariable Long id) {
        return ResponseEntity.ok(issuePriorityResourceAssembler.toResource(issuePriorityService.findOne(id)));
    }

    @PostMapping
    public ResponseEntity<IssuePriorityResource> create(@RequestBody IssuePriorityResource resource) {
        return new ResponseEntity<>(issuePriorityResourceAssembler.toResource(issuePriorityService.create(resource)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<IssuePriorityResource> update(@PathVariable Long id, @RequestBody IssuePriorityResource resource) {
        return ResponseEntity.ok(issuePriorityResourceAssembler.toResource(issuePriorityService.update(id, resource)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        issuePriorityService.delete(id);
        return ResponseEntity.ok().build();
    }

}
