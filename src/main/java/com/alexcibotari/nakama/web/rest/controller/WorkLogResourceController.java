package com.alexcibotari.nakama.web.rest.controller;

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

@RestController
@RequestMapping(path = "/api/worklogs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(WorkLogResource.class)
public class WorkLogResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private WorkLogService workLogService;

    @Autowired
    private WorkLogResourceAssembler workLogResourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<WorkLogResource>> workLogs() {
        Link link = entityLinks.linkToCollectionResource(WorkLogResource.class);
        Resources<WorkLogResource> resources = new Resources<>(workLogResourceAssembler.toResources(workLogService.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkLogResource> workLog(@PathVariable Long id) {
        return ResponseEntity.ok(workLogResourceAssembler.toResource(workLogService.findOne(id)));
    }

    @PostMapping
    public ResponseEntity<WorkLogResource> create(@RequestBody WorkLogResource resource) {
        return new ResponseEntity<>(workLogResourceAssembler.toResource(workLogService.create(resource)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkLogResource> update(@PathVariable Long id, @RequestBody WorkLogResource resource) {
        return ResponseEntity.ok(workLogResourceAssembler.toResource(workLogService.update(id, resource)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workLogService.delete(id);
        return ResponseEntity.ok().build();
    }
}
