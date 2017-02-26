package com.alexcibotari.nakama.web.rest.controller;


import com.alexcibotari.nakama.domain.User;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ExposesResourceFor(UserResource.class)
public class UserResourceController {

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

    @GetMapping("{login}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> one(@PathVariable String login) {
        return toResourceResponse(service.findOneByLogin(login));
    }

    @PostMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> create(@RequestBody UserResource resource) throws URISyntaxException {
        resource = resourceAssembler.toResource(service.create(resource));
        return ResponseEntity.created(new URI(resource.getId().getHref())).body(resource);
    }

    @PutMapping("{login}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> update(@PathVariable String login, @RequestBody UserResource resource) {
        return toResourceResponse(service.update(login, resource));
    }

    @DeleteMapping("{login}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> delete(@PathVariable String login) {
        return toResourceResponse(service.delete(login));
    }

    private ResponseEntity<UserResource> toResourceResponse(Optional<User> entity) {
        return entity.map(e -> ResponseEntity.ok(resourceAssembler.toResource(e)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("{login}/password")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> password(@PathVariable String login) {
        //TODO change logic
        return null;
    }

    @PutMapping("{login}/password/reset")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserResource> passwordReset(@PathVariable String login) {
        //TODO reset logic
        return null;
    }
}
