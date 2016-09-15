package com.alexcibotari.nakama.web.rest.controller;


import com.alexcibotari.nakama.security.AuthoritiesConstants;
import com.alexcibotari.nakama.service.UserService;
import com.alexcibotari.nakama.web.rest.assembler.UserResourceAssembler;
import com.alexcibotari.nakama.web.rest.resource.UserResource;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(UserResource.class)
public class UserResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private UserService userService;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @GetMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Resources<UserResource>> users() {
        Link link = entityLinks.linkToCollectionResource(UserResource.class);
        Resources<UserResource> resources = new Resources<>(userResourceAssembler.toResources(userService.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{id}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> user(@PathVariable String id) {
        return ResponseEntity.ok(userResourceAssembler.toResource(userService.findOneByUserName(id)));
    }

    @PostMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> create(@RequestBody UserResource resource) {
        return new ResponseEntity<>(userResourceAssembler.toResource(userService.create(resource)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> update(@PathVariable String id, @RequestBody UserResource resource) {
        return ResponseEntity.ok(userResourceAssembler.toResource(userService.update(id, resource)));
    }

    @DeleteMapping("{id}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
