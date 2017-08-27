package com.alexcibotari.nakama.web.assembler;

import com.alexcibotari.nakama.domain.User;
import com.alexcibotari.nakama.web.controller.UserResourceController;
import com.alexcibotari.nakama.web.resource.PersonalResource;
import com.alexcibotari.nakama.web.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    @Autowired
    private EntityLinks entityLinks;

    public UserResourceAssembler() {
        super(UserResourceController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User entity) {
        UserResource resource = createResourceWithId(entity.getLogin(), entity);
        resource.add(entityLinks.linkFor(UserResource.class).slash(entity.getLogin()).slash("authorities").withRel("authorities"));
        return resource;
    }

    @Override
    protected UserResource instantiateResource(User entity) {
        UserResource resource = new UserResource();
        resource.setLogin(entity.getLogin());
        resource.setEmail(entity.getEmail());
        resource.setEnabled(entity.getEnabled());
        resource.setCreatedDate(entity.getCreatedDate());
        resource.setLastModifiedDate(entity.getLastModifiedDate());

        if(entity.getPersonal() != null) {
            PersonalResource personal = new PersonalResource();
            personal.setGivenName(entity.getPersonal().getGivenName());
            personal.setFamilyName(entity.getPersonal().getFamilyName());
            personal.setBirthday(entity.getPersonal().getBirthday());
            resource.setPersonal(personal);
        }

        return resource;
    }
}
