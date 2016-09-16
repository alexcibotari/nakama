package com.alexcibotari.nakama.web.rest.assembler;

import com.alexcibotari.nakama.domain.Project;
import com.alexcibotari.nakama.web.rest.controller.ProjectResourceController;
import com.alexcibotari.nakama.web.rest.resource.ProjectResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProjectResourceAssembler extends ResourceAssemblerSupport<Project, ProjectResource> {

    @Autowired
    private EntityLinks entityLinks;

    public ProjectResourceAssembler() {
        super(ProjectResourceController.class, ProjectResource.class);
    }

    @Override
    public ProjectResource toResource(Project entity) {
        ProjectResource resource = createResourceWithId(entity.getId(), entity);
        resource.add(entityLinks.linkFor(ProjectResource.class).slash(entity.getId()).slash("issues").withRel("issues"));
        return resource;
    }

    @Override
    protected ProjectResource instantiateResource(Project entity) {
        ProjectResource resource = new ProjectResource();
        resource.setName(entity.getName());
        resource.setKey(entity.getKey());
        resource.setDescription(entity.getDescription());
        resource.setCreatedDate(entity.getCreatedDate());
        resource.setLastModifiedDate(entity.getLastModifiedDate());
        return resource;
    }
}
