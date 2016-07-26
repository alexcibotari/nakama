package com.alexcibotari.nakama.web.rest.admin;

import com.alexcibotari.nakama.service.IssueTypeService;
import com.alexcibotari.nakama.web.rest.dto.IssueTypeDTO;
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
@RequestMapping(path = "/api/admin/issues/types", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class IssueTypeResource {

    private final Logger log = LoggerFactory.getLogger(IssueTypeResource.class);

    @Autowired
    private IssueTypeService issueTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<IssueTypeDTO>> getAll() {
        return ResponseEntity.ok(issueTypeService.findAll().stream().map(IssueTypeDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<IssueTypeDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(new IssueTypeDTO(issueTypeService.findOne(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<IssueTypeDTO> create(@RequestBody IssueTypeDTO dto) {
        return new ResponseEntity<>(new IssueTypeDTO(issueTypeService.create(dto)), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<IssueTypeDTO> update(@RequestBody IssueTypeDTO dto) {
        return ResponseEntity.ok(new IssueTypeDTO(issueTypeService.update(dto)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        issueTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
