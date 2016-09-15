package com.alexcibotari.nakama.web.rest.assembler;

import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.web.rest.controller.IssueResourceController;
import com.alexcibotari.nakama.web.rest.resource.IssueResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class IssueResourceAssembler extends ResourceAssemblerSupport<Issue, IssueResource> {


    @Autowired
    private IssuePriorityResourceAssembler issuePriorityResourceAssembler;

    @Autowired
    private IssueStatusResourceAssembler issueStatusResourceAssembler;

    @Autowired
    private IssueTypeResourceAssembler issueTypeResourceAssembler;

    public IssueResourceAssembler() {
        super(IssueResourceController.class, IssueResource.class);
    }

    @Override
    public IssueResource toResource(Issue entity) {
        IssueResource resource = createResourceWithId(entity.getKey(), entity);
        return resource;
    }

    @Override
    protected IssueResource instantiateResource(Issue entity) {
        IssueResource resource = new IssueResource();
        resource.setIdInProject(entity.getIdInProject());
        resource.setProject(entity.getProject().getKey());
        resource.setSummery(entity.getSummery());
        resource.setDescription(entity.getDescription());
        resource.setPriority(issuePriorityResourceAssembler.toResource(entity.getPriority()));
        resource.setStatus(issueStatusResourceAssembler.toResource(entity.getStatus()));
        resource.setType(issueTypeResourceAssembler.toResource(entity.getType()));
        resource.setTimeSpent(entity.getTimeSpent());
        resource.setTimeEstimate(entity.getTimeEstimate());
        resource.setDueDate(entity.getDueDate());
        resource.setCreatedDate(entity.getCreatedDate());
        resource.setLastModifiedDate(entity.getLastModifiedDate());
        return resource;
    }
}
