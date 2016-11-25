package com.alexcibotari.nakama.web.rest.assembler;

import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.web.rest.controller.IssueTypeResourceController;
import com.alexcibotari.nakama.web.rest.resource.IssueTypeResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class IssueTypeResourceAssembler extends ResourceAssemblerSupport<IssueType, IssueTypeResource> {

    public IssueTypeResourceAssembler() {
        super(IssueTypeResourceController.class, IssueTypeResource.class);
    }

    @Override
    public IssueTypeResource toResource(IssueType entity) {
        IssueTypeResource resource = createResourceWithId(entity.getId(), entity);
        return resource;
    }

    @Override
    protected IssueTypeResource instantiateResource(IssueType entity) {
        IssueTypeResource resource = new IssueTypeResource();
        resource.setName(entity.getName());
        resource.setDescription(entity.getDescription());
        resource.setCreatedDate(entity.getCreatedDate());
        resource.setLastModifiedDate(entity.getLastModifiedDate());
        return resource;
    }
}
