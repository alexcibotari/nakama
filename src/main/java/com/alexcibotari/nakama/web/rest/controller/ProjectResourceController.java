package com.alexcibotari.nakama.web.rest.controller;

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

@RestController
@RequestMapping(value = "/api/projects", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(ProjectResource.class)
public class ProjectResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectResourceAssembler projectResourceAssembler;

    @Autowired
    private IssueService issueService;

    @Autowired
    private IssueResourceAssembler issueResourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<ProjectResource>> projects() {
        Link link = entityLinks.linkToCollectionResource(ProjectResource.class);
        Resources<ProjectResource> resources = new Resources<>(projectResourceAssembler.toResources(projectService.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{key}")
    public ResponseEntity<ProjectResource> project(@PathVariable String key) {
        return ResponseEntity.ok(projectResourceAssembler.toResource(projectService.findOneByKey(key)));
    }

    @PostMapping
    public ResponseEntity<ProjectResource> create(@RequestBody ProjectResource resource) {
        return new ResponseEntity<>(projectResourceAssembler.toResource(projectService.create(resource)), HttpStatus.CREATED);
    }

    @PutMapping("/{key}")
    public ResponseEntity<ProjectResource> update(@PathVariable String key, @RequestBody ProjectResource resource) {
        return ResponseEntity.ok(projectResourceAssembler.toResource(projectService.update(key, resource)));
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Void> delete(@PathVariable String key) {
        projectService.delete(key);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{key}/issues")
    public ResponseEntity<Resources<IssueResource>> issues(@PathVariable String key) {
        Link link = entityLinks.linkFor(ProjectResource.class).slash(key).slash("issues").withSelfRel();
        Resources<IssueResource> resources = new Resources<>(issueResourceAssembler.toResources(issueService.findAllByProject(key)), link);
        return ResponseEntity.ok(resources);
    }

    @PostMapping("{key}/issues")
    public ResponseEntity<IssueResource> createIssue(@PathVariable String key, @RequestBody IssueResource resource) {
        resource.setProject(key);//Set Project Key
        return new ResponseEntity<>(issueResourceAssembler.toResource(issueService.create(resource)), HttpStatus.CREATED);
    }

}
