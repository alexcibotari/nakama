package com.alexcibotari.nakama.web.controller;


import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.security.AuthoritiesConstants;
import com.alexcibotari.nakama.service.UserService;
import com.alexcibotari.nakama.web.assembler.UserResourceAssembler;
import com.alexcibotari.nakama.web.resource.AuthorityResource;
import com.alexcibotari.nakama.web.resource.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/authorities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(AuthorityResource.class)
public class AuthorityResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private UserService service;

    @Autowired
    private UserResourceAssembler resourceAssembler;

    @GetMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Resources<UserResource>> list() {
        Link link = entityLinks.linkToCollectionResource(UserResource.class);
        Resources<UserResource> resources = new Resources<>(resourceAssembler.toResources(service.findAll()), link);
        return ResponseEntity.ok(resources);
    }

    @GetMapping("{name}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> one(@PathVariable String name) {
        return toResourceResponse(service.findOneByLogin(name));
    }

    @PostMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> create(@RequestBody UserResource resource) throws URISyntaxException {
        resource = resourceAssembler.toResource(service.create(resource));
        return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
    }

    @PutMapping("{name}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> update(@PathVariable String name, @RequestBody UserResource resource) {
        return toResourceResponse(service.update(name, resource));
    }

    @DeleteMapping("{name}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> delete(@PathVariable String name) {
        return toResourceResponse(service.delete(name));
    }

    private ResponseEntity<UserResource> toResourceResponse(Optional<User> entity) {
        return entity.map(e -> ResponseEntity.ok(resourceAssembler.toResource(e)))
            .orElse(ResponseEntity.notFound().build());
    }


}
