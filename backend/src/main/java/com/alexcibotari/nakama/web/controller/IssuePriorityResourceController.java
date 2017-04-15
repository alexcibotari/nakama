package com.alexcibotari.nakama.web.controller;

import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.service.IssuePriorityService;
import com.alexcibotari.nakama.web.assembler.IssuePriorityResourceAssembler;
import com.alexcibotari.nakama.web.resource.IssuePriorityResource;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/issuepriorities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(IssuePriorityResource.class)
public class IssuePriorityResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private IssuePriorityService service;

    @Autowired
    private IssuePriorityResourceAssembler resourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<IssuePriorityResource>> list() {
        Link link = entityLinks.linkToCollectionResource(IssuePriorityResource.class);
        Resources<IssuePriorityResource> resources = new Resources<>(resourceAssembler.toResources(service.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    public ResponseEntity<IssuePriorityResource> one(@PathVariable Long id) {
        return toResourceResponse(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<IssuePriorityResource> create(@RequestBody IssuePriorityResource resource) throws URISyntaxException {
        resource = resourceAssembler.toResource(service.create(resource));
        return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
    }

    @PutMapping("{id}")
    public ResponseEntity<IssuePriorityResource> update(@PathVariable Long id, @RequestBody IssuePriorityResource resource) {
        return toResourceResponse(service.update(id, resource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IssuePriorityResource> delete(@PathVariable Long id) {
        return toResourceResponse(service.delete(id));
    }

    private ResponseEntity<IssuePriorityResource> toResourceResponse(Optional<IssuePriority> entity) {
        return entity.map(e -> ResponseEntity.ok(resourceAssembler.toResource(e)))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
