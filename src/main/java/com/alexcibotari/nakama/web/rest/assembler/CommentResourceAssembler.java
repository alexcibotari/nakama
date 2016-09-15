package com.alexcibotari.nakama.web.rest.assembler;

import com.alexcibotari.nakama.domain.Comment;
import com.alexcibotari.nakama.web.rest.controller.CommentResourceController;
import com.alexcibotari.nakama.web.rest.resource.CommentResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class CommentResourceAssembler extends ResourceAssemblerSupport<Comment, CommentResource> {

    public CommentResourceAssembler() {
        super(CommentResourceController.class, CommentResource.class);
    }

    @Override
    public CommentResource toResource(Comment entity) {
        CommentResource resource = createResourceWithId(entity.getId(), entity);
        return resource;
    }

    @Override
    protected CommentResource instantiateResource(Comment entity) {
        CommentResource resource = new CommentResource();
        resource.setIssue(entity.getIssue().getKey());
        resource.setContent(entity.getContent());
        resource.setCreatedDate(entity.getCreatedDate());
        resource.setLastModifiedDate(entity.getLastModifiedDate());
        return resource;
    }
}
