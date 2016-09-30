package com.alexcibotari.nakama.web.rest.controller;

import com.alexcibotari.nakama.domain.WorkLog;
import com.alexcibotari.nakama.service.WorkLogService;
import com.alexcibotari.nakama.web.rest.assembler.WorkLogResourceAssembler;
import com.alexcibotari.nakama.web.rest.resource.WorkLogResource;
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
@RequestMapping(path = "/api/worklogs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(WorkLogResource.class)
public class WorkLogResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private WorkLogService service;

    @Autowired
    private WorkLogResourceAssembler resourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<WorkLogResource>> list() {
        Link link = entityLinks.linkToCollectionResource(WorkLogResource.class);
        Resources<WorkLogResource> resources = new Resources<>(resourceAssembler.toResources(service.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkLogResource> one(@PathVariable Long id) {
        return toResourceResponse(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<WorkLogResource> create(@RequestBody WorkLogResource resource) throws URISyntaxException {
        resource = resourceAssembler.toResource(service.create(resource));
        return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkLogResource> update(@PathVariable Long id, @RequestBody WorkLogResource resource) {
        return toResourceResponse(service.update(id, resource));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<WorkLogResource> delete(@PathVariable Long id) {
        return toResourceResponse(service.delete(id));

    }

    private ResponseEntity<WorkLogResource> toResourceResponse(Optional<WorkLog> entity) {
        return entity.map(e -> ResponseEntity.ok(resourceAssembler.toResource(e)))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
