package com.alexcibotari.nakama.web.rest.assembler;

import com.alexcibotari.nakama.domain.WorkLog;
import com.alexcibotari.nakama.web.rest.controller.WorkLogResourceController;
import com.alexcibotari.nakama.web.rest.resource.IssueResource;
import com.alexcibotari.nakama.web.rest.resource.WorkLogResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class WorkLogResourceAssembler extends ResourceAssemblerSupport<WorkLog, WorkLogResource> {

    @Autowired
    private EntityLinks entityLinks;

    public WorkLogResourceAssembler() {
        super(WorkLogResourceController.class, WorkLogResource.class);
    }

    @Override
    public WorkLogResource toResource(WorkLog entity) {
        WorkLogResource resource = createResourceWithId(entity.getId(), entity);
        resource.add(entityLinks.linkFor(IssueResource.class).slash(entity.getIssue().getKey()).withRel("issue"));
        return resource;
    }

    @Override
    protected WorkLogResource instantiateResource(WorkLog entity) {
        WorkLogResource resource = new WorkLogResource();
        resource.setContent(entity.getContent());
        resource.setTimeWorked(entity.getTimeWorked());
        resource.setStartDate(entity.getStartDate());
        resource.setCreatedDate(entity.getCreatedDate());
        resource.setLastModifiedDate(entity.getLastModifiedDate());
        return resource;
    }
}
