package com.alexcibotari.nakama.web.rest;


import com.alexcibotari.nakama.security.AuthoritiesConstants;
import com.alexcibotari.nakama.web.rest.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> createUser(@RequestBody UserDTO user, HttpServletRequest request) throws URISyntaxException {
        return null;
    }
}
