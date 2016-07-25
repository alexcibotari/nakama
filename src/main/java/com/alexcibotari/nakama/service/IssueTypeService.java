package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.web.rest.dto.IssueTypeDTO;

import java.util.List;

public interface IssueTypeService {

    IssueType findOne(Long id);

    List<IssueType> findAll();

    IssueType create(IssueTypeDTO dto);

    IssueType update(IssueTypeDTO dto);

    void delete(Long id);
}
