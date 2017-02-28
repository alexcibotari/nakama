package com.alexcibotari.nakama.web.controller;

import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.service.IssueTypeService;
import com.alexcibotari.nakama.web.assembler.IssueTypeResourceAssembler;
import com.alexcibotari.nakama.web.resource.IssueTypeResource;
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
@RequestMapping(path = "/api/issuetypes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(IssueTypeResource.class)
public class IssueTypeResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private IssueTypeService service;

    @Autowired
    private IssueTypeResourceAssembler resourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<IssueTypeResource>> list() {
        Link link = entityLinks.linkToCollectionResource(IssueTypeResource.class);
        Resources<IssueTypeResource> resources = new Resources<>(resourceAssembler.toResources(service.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    public ResponseEntity<IssueTypeResource> one(@PathVariable Long id) {
        return toResourceResponse(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<IssueTypeResource> create(@RequestBody IssueTypeResource resource) throws URISyntaxException {
        resource = resourceAssembler.toResource(service.create(resource));
        return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
    }

    @PutMapping("{id}")
    public ResponseEntity<IssueTypeResource> update(@PathVariable Long id, @RequestBody IssueTypeResource resource) {
        return toResourceResponse(service.update(id, resource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IssueTypeResource> delete(@PathVariable Long id) {
        return toResourceResponse(service.delete(id));
    }

    private ResponseEntity<IssueTypeResource> toResourceResponse(Optional<IssueType> entity) {
        return entity.map(e -> ResponseEntity.ok(resourceAssembler.toResource(e)))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
