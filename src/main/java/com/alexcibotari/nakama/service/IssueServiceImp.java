package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.domain.IssuePriority;
import com.alexcibotari.nakama.domain.IssueStatus;
import com.alexcibotari.nakama.domain.IssueType;
import com.alexcibotari.nakama.repository.*;
import com.alexcibotari.nakama.service.util.key.IssueKey;
import com.alexcibotari.nakama.service.util.key.KeyUtil;
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

    @Autowired
    private IssuePriorityRepository issuePriorityRepository;

    @Autowired
    private IssueStatusRepository issueStatusRepository;

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

        if (dto.getPriority() != null && dto.getPriority().getId() != null) {
            IssuePriority one = issuePriorityRepository.findOne(dto.getPriority().getId());
            issue.setPriority(one);
        }

        if (dto.getStatus() != null && dto.getStatus().getId() != null) {
            IssueStatus one = issueStatusRepository.findOne(dto.getStatus().getId());
            issue.setStatus(one);
        }

        if (dto.getType() != null && dto.getType().getId() != null) {
            IssueType one = issueTypeRepository.findOne(dto.getType().getId());
            issue.setType(one);
        }

        return issueRepository.save(issue);
    }

    @Transactional
    public Issue update(IssueDTO dto) {
        Issue issue = findOne(dto.getProject(), dto.getIdInProject());
        issue.setSummery(dto.getSummery());
        issue.setDescription(dto.getDescription());

        if (dto.getPriority() != null && dto.getPriority().getId() != null) {
            IssuePriority one = issuePriorityRepository.findOne(dto.getPriority().getId());
            issue.setPriority(one);
        }

        if (dto.getStatus() != null && dto.getStatus().getId() != null) {
            IssueStatus one = issueStatusRepository.findOne(dto.getStatus().getId());
            issue.setStatus(one);
        }

        if (dto.getType() != null && dto.getType().getId() != null) {
            IssueType one = issueTypeRepository.findOne(dto.getType().getId());
            issue.setType(one);
        }

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
