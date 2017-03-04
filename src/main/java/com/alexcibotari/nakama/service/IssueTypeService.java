package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.web.resource.IssueTypeResource;

import java.util.List;
import java.util.Optional;

public interface IssueTypeService {

    Optional<IssueType> findOne(Long id);

    List<IssueType> findAll();

    IssueType create(IssueTypeResource resource);

    Optional<IssueType> update(Long id, IssueTypeResource resource);

    Optional<IssueType> delete(Long id);
}
