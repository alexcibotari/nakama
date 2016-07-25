package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueType;

import java.util.List;

public interface IssueTypeService {

    IssueType findOne(Long id);

    List<IssueType> findAll();
}
