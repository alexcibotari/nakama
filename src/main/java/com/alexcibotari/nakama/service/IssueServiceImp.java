package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.domain.Project;
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

    public Issue findOne(String key) {
        return null;
    }

    public Issue findOne(String projectKey, Long idInProject) {
        return issueRepository.findOneByKeys(projectKey, idInProject);
    }

    public List<Issue> findAllByProjectId(Long id) {
        return issueRepository.findAllByProjectId(id);
    }

    public List<Issue> findAllByProjectKey(String key) {
        return issueRepository.findAllByProjectKey(key);
    }

    public List<Issue> findAll() {
        return (List<Issue>) issueRepository.findAll();
    }

    @Transactional
    public Issue create(IssueDTO dto) {
        Issue issue = new Issue();
        issue.setProject(projectRepository.findOneByKey(dto.getProject()));
        issue.setIdInProject(issueRepository.getNextInProjectIdByProjectKey(dto.getProject()));
        issue.setSummery(dto.getSummery());
        issue.setDescription(dto.getDescription());
        return issueRepository.save(issue);
    }

    @Transactional
    public Issue update(IssueDTO dto) {
        Issue issue = findOne(dto.getProject(), dto.getIdInProject());
        issue.setSummery(dto.getSummery());
        issue.setDescription(dto.getDescription());
        return issueRepository.save(issue);
    }

    @Transactional
    public void delete(Long id) {
        issueRepository.delete(id);
    }

    @Transactional
    public void delete(String projectKey, Long idInProject) {
        Issue issue = issueRepository.findOneByKeys(projectKey, idInProject);
        issueRepository.delete(issue);
    }
}