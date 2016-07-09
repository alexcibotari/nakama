package com.alexcibotari.nakama.web.rest;

import com.alexcibotari.nakama.service.ProjectService;
import com.alexcibotari.nakama.web.rest.dto.ProjectDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDTO> getAll() {
        return projectService.findAll().stream().map(ProjectDTO::new).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO getOne(@PathVariable String key) {
        return new ProjectDTO(projectService.findOneByKey(key));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO create(@RequestBody ProjectDTO dto) {
        return new ProjectDTO(projectService.create(dto));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO update(@RequestBody ProjectDTO dto){
        return new ProjectDTO(projectService.update(dto));
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String key){
        projectService.delete(key);
    }

}
