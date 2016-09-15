package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.web.rest.resource.IssueTypeResource;

import java.util.List;

public interface IssueTypeService {

    IssueType findOne(Long id);

    List<IssueType> findAll();

    IssueType create(IssueTypeResource resource);

    IssueType update(Long id, IssueTypeResource resource);

    void delete(Long id);
}
