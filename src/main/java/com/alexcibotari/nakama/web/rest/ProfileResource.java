package com.alexcibotari.nakama.web.rest;


import com.alexcibotari.nakama.domain.User;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProfileResource {

    private final Logger log = LoggerFactory.getLogger(ProfileResource.class);

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getUser();
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }
}
