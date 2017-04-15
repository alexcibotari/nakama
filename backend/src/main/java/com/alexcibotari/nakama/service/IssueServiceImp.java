package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.domain.IssueStatus;
import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.repository.IssuePriorityRepository;
import com.alexcibotari.nakama.repository.IssueRepository;
import com.alexcibotari.nakama.repository.IssueStatusRepository;
import com.alexcibotari.nakama.repository.IssueTypeRepository;
import com.alexcibotari.nakama.web.resource.IssueResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IssueServiceImp implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IssuePriorityRepository priorityRepository;

    @Autowired
    private IssueStatusRepository statusRepository;

    @Autowired
    private IssueTypeRepository issueTypeRepository;


    public Optional<Issue> findOne(Long id) {
        return issueRepository.findOneById(id);
    }

    public Optional<Issue> findOne(String key) {
        return issueRepository.findOne(key);
    }

    public Optional<Issue> findOne(String projectKey, Long idInProject) {
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
        issue.setProject(projectService.findOne(resource.getProject()).get());//TODO to Optional
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
    public Optional<Issue> update(String key, IssueResource resource) {
        return findOne(key).map(entity -> {
            entity.setSummery(resource.getSummery());
            entity.setDescription(resource.getDescription());

            if (resource.getPriority() != null && resource.getPriority().getId() != null) {
                IssuePriority one = null;//issuePriorityRepository.findOne(resource.getPriority().getId());
                entity.setPriority(one);
            }

            if (resource.getStatus() != null && resource.getStatus().getId() != null) {
                IssueStatus one = null;//issueStatusRepository.findOne(resource.getStatus().getId());
                entity.setStatus(one);
            }

            if (resource.getType() != null && resource.getType().getId() != null) {
                IssueType one = null;//issueTypeRepository.findOne(resource.getType().getId());
                entity.setType(one);
            }

            return issueRepository.save(entity);
        });

    }

    @Transactional
    public Optional<Issue> delete(Long id) {
        return issueRepository.deleteOneById(id);
    }

    @Transactional
    public Optional<Issue> delete(String key) {
        return findOne(key)
            .map(entity -> {
                delete(entity.getId());
                return entity;
            });
    }

    @Transactional
    public Optional<Issue> delete(String projectKey, Long idInProject) {
        return findOne(projectKey, idInProject)
            .map(entity -> {
                delete(entity.getId());
                return entity;
            });
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
