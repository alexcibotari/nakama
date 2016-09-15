package com.alexcibotari.nakama.web.rest.assembler;

import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.web.rest.controller.IssuePriorityResourceController;
import com.alexcibotari.nakama.web.rest.resource.IssuePriorityResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class IssuePriorityResourceAssembler extends ResourceAssemblerSupport<IssuePriority, IssuePriorityResource> {

    public IssuePriorityResourceAssembler() {
        super(IssuePriorityResourceController.class, IssuePriorityResource.class);
    }

    @Override
    public IssuePriorityResource toResource(IssuePriority entity) {
        IssuePriorityResource resource = createResourceWithId(entity.getId(), entity);
        return resource;
    }

    @Override
    protected IssuePriorityResource instantiateResource(IssuePriority entity) {
        IssuePriorityResource resource = new IssuePriorityResource();
        resource.setName(entity.getName());
        resource.setDescription(entity.getDescription());
        resource.setCreatedDate(entity.getCreatedDate());
        resource.setLastModifiedDate(entity.getLastModifiedDate());
        return resource;
    }
}
