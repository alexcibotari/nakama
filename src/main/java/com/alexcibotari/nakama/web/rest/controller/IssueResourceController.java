package com.alexcibotari.nakama.web.rest.controller;

import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.service.CommentService;
import com.alexcibotari.nakama.service.IssueService;
import com.alexcibotari.nakama.service.WorkLogService;
import com.alexcibotari.nakama.web.rest.assembler.CommentResourceAssembler;
import com.alexcibotari.nakama.web.rest.assembler.IssueResourceAssembler;
import com.alexcibotari.nakama.web.rest.assembler.WorkLogResourceAssembler;
import com.alexcibotari.nakama.web.rest.resource.CommentResource;
import com.alexcibotari.nakama.web.rest.resource.IssueResource;
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
@RequestMapping(path = "/api/issues", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(IssueResource.class)
public class IssueResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private IssueResourceAssembler issueResourceAssembler;

    @Autowired
    private IssueService issueService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentResourceAssembler commentResourceAssembler;

    @Autowired
    private WorkLogService workLogService;

    @Autowired
    private WorkLogResourceAssembler workLogResourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<IssueResource>> list() {
        Link link = entityLinks.linkToCollectionResource(IssueResource.class);
        Resources<IssueResource> resources = new Resources<>(issueResourceAssembler.toResources(issueService.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{key}")
    public ResponseEntity<IssueResource> one(@PathVariable String key) {
        return toResourceResponse(issueService.findOne(key));
    }

    @PostMapping
    public ResponseEntity<IssueResource> create(@RequestBody IssueResource resource) throws URISyntaxException {
        resource = issueResourceAssembler.toResource(issueService.create(resource));
        return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
    }

    @PutMapping("{key}")
    public ResponseEntity<IssueResource> update(@PathVariable String key, @RequestBody IssueResource resource) {
        return toResourceResponse(issueService.update(key, resource));
    }

    @DeleteMapping("{key}")
    public ResponseEntity<IssueResource> delete(@PathVariable String key) {
        return toResourceResponse(issueService.delete(key));
    }

    private ResponseEntity<IssueResource> toResourceResponse(Optional<Issue> entity) {
        return entity.map(e -> ResponseEntity.ok(issueResourceAssembler.toResource(e)))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("{key}/comments")
    public ResponseEntity<Resources<CommentResource>> comments(@PathVariable String key) {
        Link link = entityLinks.linkFor(IssueResource.class).slash(key).slash("comments").withSelfRel();
        Resources<CommentResource> resources = new Resources<>(commentResourceAssembler.toResources(commentService.findAllByIssue(key)), link);
        return ResponseEntity.ok(resources);
    }

    @PostMapping("{key}/comments")
    public ResponseEntity<CommentResource> createComment(@PathVariable String key, @RequestBody CommentResource resource) {
        resource.setIssue(key);//Set Issue Key
        return new ResponseEntity<>(commentResourceAssembler.toResource(commentService.create(resource)), HttpStatus.CREATED);
    }

    @GetMapping("{key}/worklogs")
    public ResponseEntity<Resources<WorkLogResource>> workLogs(@PathVariable String key) {
        Link link = entityLinks.linkFor(IssueResource.class).slash(key).slash("worklogs").withSelfRel();
        Resources<WorkLogResource> resources = new Resources<>(workLogResourceAssembler.toResources(workLogService.findAllByIssue(key)), link);
        return ResponseEntity.ok(resources);
    }

    @PostMapping("{key}/worklogs")
    public ResponseEntity<WorkLogResource> createWorkLog(@PathVariable String key, @RequestBody WorkLogResource resource) {
        resource.setIssue(key);//Set Issue Key
        return new ResponseEntity<>(workLogResourceAssembler.toResource(workLogService.create(resource)), HttpStatus.CREATED);
    }
}
