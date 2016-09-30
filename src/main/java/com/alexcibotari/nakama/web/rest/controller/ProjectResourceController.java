package com.alexcibotari.nakama.web.rest.controller;

import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.service.IssueService;
import com.alexcibotari.nakama.service.ProjectService;
import com.alexcibotari.nakama.web.rest.assembler.IssueResourceAssembler;
import com.alexcibotari.nakama.web.rest.assembler.ProjectResourceAssembler;
import com.alexcibotari.nakama.web.rest.resource.IssueResource;
import com.alexcibotari.nakama.web.rest.resource.ProjectResource;
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
@RequestMapping(value = "/api/projects", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(ProjectResource.class)
public class ProjectResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private ProjectService service;

    @Autowired
    private ProjectResourceAssembler resourceAssembler;

    @Autowired
    private IssueService issueService;

    @Autowired
    private IssueResourceAssembler issueResourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<ProjectResource>> list() {
        Link link = entityLinks.linkToCollectionResource(ProjectResource.class);
        Resources<ProjectResource> resources = new Resources<>(resourceAssembler.toResources(service.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{key}")
    public ResponseEntity<ProjectResource> one(@PathVariable String key) {
        return toResourceResponse(service.findOne(key));
    }

    @PostMapping
    public ResponseEntity<ProjectResource> create(@RequestBody ProjectResource resource) throws URISyntaxException {
        resource = resourceAssembler.toResource(service.create(resource));
        return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
    }

    @PutMapping("/{key}")
    public ResponseEntity<ProjectResource> update(@PathVariable String key, @RequestBody ProjectResource resource) {
        return toResourceResponse(service.update(key, resource));
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<ProjectResource> delete(@PathVariable String key) {
        return toResourceResponse(service.delete(key));
    }

    private ResponseEntity<ProjectResource> toResourceResponse(Optional<Project> entity) {
        return entity.map(e -> ResponseEntity.ok(resourceAssembler.toResource(e)))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("{key}/issues")
    public ResponseEntity<Resources<IssueResource>> issues(@PathVariable String key) {
        Link link = entityLinks.linkFor(ProjectResource.class).slash(key).slash("issues").withSelfRel();
        Resources<IssueResource> resources = new Resources<>(issueResourceAssembler.toResources(issueService.findAllByProject(key)), link);
        return ResponseEntity.ok(resources);
    }

    @PostMapping("{key}/issues")
    public ResponseEntity<IssueResource> createIssue(@PathVariable String key, @RequestBody IssueResource resource) throws URISyntaxException {
        resource.setProject(key);//Set Project Key
        resource = issueResourceAssembler.toResource(issueService.create(resource));
        return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
    }
}
