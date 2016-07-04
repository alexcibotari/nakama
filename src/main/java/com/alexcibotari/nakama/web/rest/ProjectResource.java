package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    @Autowired
    private ProjectService projectService;

   /* @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getAll() {

    }*/
}
