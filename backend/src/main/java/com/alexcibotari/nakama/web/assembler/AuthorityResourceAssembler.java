package com.alexcibotari.nakama.web.assembler;

import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.web.controller.AuthorityResourceController;
import com.alexcibotari.nakama.web.resource.AuthorityResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class AuthorityResourceAssembler extends ResourceAssemblerSupport<Authority, AuthorityResource> {

    @Autowired
    private EntityLinks entityLinks;

    public AuthorityResourceAssembler() {
        super(AuthorityResourceController.class, AuthorityResource.class);
    }

    @Override
    public AuthorityResource toResource(Authority entity) {
        AuthorityResource resource = createResourceWithId(entity.getName(), entity);
        return resource;
    }

    @Override
    protected AuthorityResource instantiateResource(Authority entity) {
        return new AuthorityResource(entity.getName());
    }
}
