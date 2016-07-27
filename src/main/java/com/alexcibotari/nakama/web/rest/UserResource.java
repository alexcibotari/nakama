package com.alexcibotari.nakama.web.rest;


import com.alexcibotari.nakama.security.AuthoritiesConstants;
import com.alexcibotari.nakama.service.UserService;
import com.alexcibotari.nakama.web.rest.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    UserService userService;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.findAll().stream().map(UserDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(path = "/users/{userName}", method = RequestMethod.GET)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserDTO> getOne(@PathVariable String userName) {
        return ResponseEntity.ok(new UserDTO(userService.findOneByUserName(userName)));
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        return new ResponseEntity<>(new UserDTO(userService.create(dto)), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/users", method = RequestMethod.PUT)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(new UserDTO(userService.update(dto)));
    }

    @RequestMapping(path = "/users/{userName}", method = RequestMethod.DELETE)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> delete(@PathVariable String userName) {
        userService.delete(userName);
        return ResponseEntity.ok().build();
    }
}
