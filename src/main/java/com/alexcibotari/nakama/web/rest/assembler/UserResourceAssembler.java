package com.alexcibotari.nakama.web.rest.assembler;

import com.alexcibotari.nakama.domain.Authority;
import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.web.rest.controller.UserResourceController;
import com.alexcibotari.nakama.web.rest.resource.UserResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserResourceController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User entity) {
        UserResource resource = createResourceWithId(entity.getUserName(), entity);
        return resource;
    }

    @Override
    protected UserResource instantiateResource(User entity) {
        UserResource resource = new UserResource();
        resource.setUserName(entity.getUserName());
        resource.setEmail(entity.getEmail());
        resource.setAuthorities(entity.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
        resource.setCreatedDate(entity.getCreatedDate());
        resource.setLastModifiedDate(entity.getLastModifiedDate());
        return resource;
    }
}
