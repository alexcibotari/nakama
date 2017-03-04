package com.alexcibotari.nakama.web.assembler;

import com.alexcibotari.nakama.domain.IssueStatus;
import com.alexcibotari.nakama.web.controller.IssueStatusResourceController;
import com.alexcibotari.nakama.web.resource.IssueStatusResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class IssueStatusResourceAssembler extends ResourceAssemblerSupport<IssueStatus, IssueStatusResource> {

    public IssueStatusResourceAssembler() {
        super(IssueStatusResourceController.class, IssueStatusResource.class);
    }

    @Override
    public IssueStatusResource toResource(IssueStatus entity) {
        IssueStatusResource resource = createResourceWithId(entity.getId(), entity);
        return resource;
    }

    @Override
    protected IssueStatusResource instantiateResource(IssueStatus entity) {
        IssueStatusResource resource = new IssueStatusResource();
        resource.setName(entity.getName());
        resource.setDescription(entity.getDescription());
        resource.setCreatedDate(entity.getCreatedDate());
        resource.setLastModifiedDate(entity.getLastModifiedDate());
        return resource;
    }
}
