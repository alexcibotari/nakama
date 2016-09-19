package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.domain.IssueStatus;
import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.repository.*;
import com.alexcibotari.nakama.service.util.key.IssueKey;
import com.alexcibotari.nakama.service.util.key.KeyUtil;
import com.alexcibotari.nakama.web.rest.resource.IssueResource;
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

    @Autowired
    private IssuePriorityRepository priorityRepository;

    @Autowired
    private IssueStatusRepository statusRepository;

    @Autowired
    private IssueTypeRepository issueTypeRepository;


    public Issue findOne(Long id) {
        return issueRepository.findOne(id);
    }

    public Issue findOne(String key) {
        IssueKey issueKey = KeyUtil.getIssueKey(key);
        if (issueKey != null && issueKey.isValid()) {
            return findOne(issueKey.getProjectKey(), issueKey.getIdInProject());
        }
        return null;
    }

    public Issue findOne(String projectKey, Long idInProject) {
        return issueRepository.findOne(projectKey, idInProject);
    }

    public List<Issue> findAllByProject(Long id) {
        return issueRepository.findAllByProjectId(id);
    }

    public List<Issue> findAllByProject(String key) {
        return issueRepository.findAllByProjectKey(key);
    }

    public List<Issue> findAll() {
        return (List<Issue>) issueRepository.findAll();
    }

    @Transactional
    public Issue create(IssueResource resource) {
        Issue issue = new Issue();
        issue.setProject(projectRepository.findOneByKey(resource.getProject()));
        issue.setSummery(resource.getSummery());
        issue.setDescription(resource.getDescription());

        if (resource.getPriority() != null && resource.getPriority().getId() != null) {
            IssuePriority one = null;//issuePriorityRepository.findOne(resource.getPriority().getId());
            issue.setPriority(one);
        }

        if (resource.getStatus() != null && resource.getStatus().getId() != null) {
            IssueStatus one = null; //issueStatusRepository.findOne(resource.getStatus().getId());
            issue.setStatus(one);
        }

        if (resource.getType() != null && resource.getType().getId() != null) {
            IssueType one = null; //issueTypeRepository.findOne(resource.getType().getId());
            issue.setType(one);
        }
        issue.setTimeSpent(0L);
        issue.setIdInProject(issueRepository.getNextIdInProject(resource.getProject()));
        return issueRepository.save(issue);
    }

    @Transactional
    public Issue update(String key, IssueResource resource) {
        Issue issue = findOne(key);
        issue.setSummery(resource.getSummery());
        issue.setDescription(resource.getDescription());

        if (resource.getPriority() != null && resource.getPriority().getId() != null) {
            IssuePriority one = null;//issuePriorityRepository.findOne(resource.getPriority().getId());
            issue.setPriority(one);
        }

        if (resource.getStatus() != null && resource.getStatus().getId() != null) {
            IssueStatus one = null;//issueStatusRepository.findOne(resource.getStatus().getId());
            issue.setStatus(one);
        }

        if (resource.getType() != null && resource.getType().getId() != null) {
            IssueType one = null;//issueTypeRepository.findOne(resource.getType().getId());
            issue.setType(one);
        }

        return issueRepository.save(issue);
    }

    @Transactional
    public void delete(Long id) {
        issueRepository.delete(id);
    }

    @Transactional
    public void delete(String key) {
        Issue issue = issueRepository.findOne(key);
        issueRepository.delete(issue);
    }

    @Transactional
    public void delete(String projectKey, Long idInProject) {
        Issue issue = issueRepository.findOne(projectKey, idInProject);
        issueRepository.delete(issue);
    }

    @Transactional
    public Issue recalculateTimeSpent(Issue issue) {
        Long value = issueRepository.calculateWorkLog(issue.getId());
        if (value == null || value == 0) {
            return issue;
        }
        issue.setTimeSpent(value);
        return issueRepository.save(issue);
    }
}
