package com.alexcibotari.nakama.web.rest.controller;

import com.alexcibotari.nakama.domain.IssueStatus;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/issuestatuses", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(IssueStatusResource.class)
public class IssueStatusResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private IssueStatusService service;

    @Autowired
    private IssueStatusResourceAssembler resourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<IssueStatusResource>> list() {
        Link link = entityLinks.linkToCollectionResource(IssueStatusResource.class);
        Resources<IssueStatusResource> resources = new Resources<>(resourceAssembler.toResources(service.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    public ResponseEntity<IssueStatusResource> one(@PathVariable Long id) {
        return toResourceResponse(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<IssueStatusResource> create(@RequestBody IssueStatusResource resource) throws URISyntaxException {
        resource = resourceAssembler.toResource(service.create(resource));
        return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
    }

    @PutMapping("{id}")
    public ResponseEntity<IssueStatusResource> update(@PathVariable Long id, @RequestBody IssueStatusResource resource) {
        return toResourceResponse(service.update(id, resource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IssueStatusResource> delete(@PathVariable Long id) {
        return toResourceResponse(service.delete(id));
    }

    private ResponseEntity<IssueStatusResource> toResourceResponse(Optional<IssueStatus> entity) {
        return entity.map(e -> ResponseEntity.ok(resourceAssembler.toResource(e)))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
