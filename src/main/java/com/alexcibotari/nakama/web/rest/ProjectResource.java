package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.service.ProjectService;
import com.alexcibotari.nakama.web.rest.dto.ProjectDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/projects", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    @Autowired
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDTO>> getAll() {
        List<ProjectDTO> dtoList = projectService.findAll().stream().map(ProjectDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProjectDTO> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(new ProjectDTO(projectService.findOne(id)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProjectDTO> create(@RequestBody ProjectDTO dto) {
        return new ResponseEntity<>(new ProjectDTO(projectService.create(dto)), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ProjectDTO> update(@RequestBody ProjectDTO dto) {
        return new ResponseEntity<>(new ProjectDTO(projectService.update(dto)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.ok().build();
    }

}
