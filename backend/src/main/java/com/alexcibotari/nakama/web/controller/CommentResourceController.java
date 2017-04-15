package com.alexcibotari.nakama.web.controller;

import com.alexcibotari.nakama.domain.Comment;
import com.alexcibotari.nakama.service.CommentService;
import com.alexcibotari.nakama.web.assembler.CommentResourceAssembler;
import com.alexcibotari.nakama.web.resource.CommentResource;
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
@RequestMapping(path = "/api/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(CommentResource.class)
public class CommentResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private CommentService service;

    @Autowired
    private CommentResourceAssembler resourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<CommentResource>> list() {
        Link link = entityLinks.linkToCollectionResource(CommentResource.class);
        Resources<CommentResource> resources = new Resources<>(resourceAssembler.toResources(service.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentResource> one(@PathVariable Long id) {
        return toResourceResponse(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<CommentResource> create(@RequestBody CommentResource resource) throws URISyntaxException {
        resource = resourceAssembler.toResource(service.create(resource));
        return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentResource> update(@PathVariable Long id, @RequestBody CommentResource resource) {
        return toResourceResponse(service.update(id, resource));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommentResource> delete(@PathVariable Long id) {
        return toResourceResponse(service.delete(id));
    }

    private ResponseEntity<CommentResource> toResourceResponse(Optional<Comment> entity) {
        return entity.map(e -> ResponseEntity.ok(resourceAssembler.toResource(e)))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
