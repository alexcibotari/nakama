package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.repository.IssueRepository;
import com.alexcibotari.nakama.repository.ProjectRepository;
import com.alexcibotari.nakama.web.rest.dto.IssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IssueServiceImp implements IssueService {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    ProjectRepository projectRepository;


    public Issue findOne(Long id) {
        return issueRepository.findOne(id);
    }

    public Issue findOneByKeys(String projectKey, Long issueKey){
        return issueRepository.findOneByKeys(projectKey, issueKey);
    }

    public List<Issue> findAllByProjectId(Long id) {
        return issueRepository.findAllByProjectId(id);
    }

    public List<Issue> findAll() {
        return (List<Issue>) issueRepository.findAll();
    }

    @Transactional
    public Issue create(IssueDTO dto) {
        Issue issue = new Issue();
        issue.setProject(projectRepository.findOne(dto.getProject()));
        issue.setKey(dto.getKey());
        issue.setSummery(dto.getSummery());
        issue.setDescription(dto.getDescription());
        return issueRepository.save(issue);
    }

    @Transactional
    public Issue update(IssueDTO dto) {
        Issue issue = issueRepository.findOne(dto.getId());
        issue.setProject(projectRepository.findOne(dto.getProject()));
        issue.setKey(dto.getKey());
        issue.setSummery(dto.getSummery());
        issue.setDescription(dto.getDescription());
        return issueRepository.save(issue);
    }

    @Transactional
    public void delete(Long id) {
        issueRepository.delete(id);
    }
}