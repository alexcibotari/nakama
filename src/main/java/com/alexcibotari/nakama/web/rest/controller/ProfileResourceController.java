
package com.alexcibotari.nakama.web.rest.controller;


import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.service.UserService;
import com.alexcibotari.nakama.web.rest.assembler.UserResourceAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProfileResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @GetMapping(path = "me")
    public ResponseEntity<?> me() {
        User user = userService.getUser();
        return new ResponseEntity<>(userResourceAssembler.toResource(user), HttpStatus.OK);
    }
}
