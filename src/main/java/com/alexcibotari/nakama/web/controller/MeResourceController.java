
package com.alexcibotari.nakama.web.controller;


import com.alexcibotari.nakama.service.UserService;
import com.alexcibotari.nakama.web.assembler.UserResourceAssembler;
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
public class MeResourceController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @GetMapping(path = "me")
    public ResponseEntity<?> me() {
        return userService.getUser().map(user -> ResponseEntity.ok(userResourceAssembler.toResource(user))).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
