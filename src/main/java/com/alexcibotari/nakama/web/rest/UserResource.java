package com.alexcibotari.nakama.web.rest;


import com.alexcibotari.nakama.security.AuthoritiesConstants;
import com.alexcibotari.nakama.service.UserService;
import com.alexcibotari.nakama.web.rest.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.findAll().stream().map(UserDTO::new).collect(Collectors.toList()));
    }
}
