package com.alexcibotari.nakama.web.rest.controller;

import com.alexcibotari.nakama.service.CommentService;
import com.alexcibotari.nakama.web.rest.assembler.CommentResourceAssembler;
import com.alexcibotari.nakama.web.rest.resource.CommentResource;
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
@RequestMapping(path = "/api/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(CommentResource.class)
public class CommentResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentResourceAssembler commentResourceAssembler;

    @GetMapping
    public ResponseEntity<Resources<CommentResource>> comments() {
        Link link = entityLinks.linkToCollectionResource(CommentResource.class);
        Resources<CommentResource> resources = new Resources<>(commentResourceAssembler.toResources(commentService.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentResource> comment(@PathVariable Long id) {
        return ResponseEntity.ok(commentResourceAssembler.toResource(commentService.findOne(id)));
    }

    @PostMapping
    public ResponseEntity<CommentResource> create(@RequestBody CommentResource dto) {
        return new ResponseEntity<>(commentResourceAssembler.toResource(commentService.create(dto)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentResource> update(@PathVariable Long id, @RequestBody CommentResource resource) {
        return ResponseEntity.ok(commentResourceAssembler.toResource(commentService.update(id, resource)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
