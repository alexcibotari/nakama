package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueWorklog;
import com.alexcibotari.nakama.repository.IssueWorklogRepository;
import com.alexcibotari.nakama.service.util.key.IssueKey;
import com.alexcibotari.nakama.service.util.key.KeyUtil;
import com.alexcibotari.nakama.web.rest.dto.IssueWorklogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IssueWorklogServiceImp implements IssueWorklogService {


    @Autowired
    IssueWorklogRepository issueWorklogRepository;

    @Autowired
    IssueService issueService;


    public IssueWorklog findOne(Long id) {
        return issueWorklogRepository.findOne(id);
    }

    public IssueWorklog findOne(String projectKey, Long idInProject, Long idInIssue) {
        return issueWorklogRepository.findOne(projectKey, idInProject, idInIssue);
    }

    public List<IssueWorklog> findAll() {
        return (List<IssueWorklog>) issueWorklogRepository.findAll();
    }

    public List<IssueWorklog> findAllInIssue(String projectKey, Long idInProject) {
        return issueWorklogRepository.findAll(projectKey, idInProject);
    }

    public List<IssueWorklog> findAllInIssue(String key) {
        IssueKey issueKey = KeyUtil.getIssueKey(key);
        if (issueKey != null && issueKey.isValid()) {
            return findAllInIssue(issueKey.getProjectKey(), issueKey.getIdInProject());
        }
        return null;
    }

    @Transactional
    public IssueWorklog create(IssueWorklogDTO dto) {
        IssueWorklog issueWorklog = new IssueWorklog();
        issueWorklog.setIssue(issueService.findOne(dto.getIssue()));
        issueWorklog.setContent(dto.getContent());
        return issueWorklogRepository.save(issueWorklog);
    }


    @Transactional
    public IssueWorklog update(IssueWorklogDTO dto) {
        IssueWorklog issueWorklog = findOne(dto.getId());
        issueWorklog.setContent(dto.getContent());
        return issueWorklogRepository.save(issueWorklog);
    }

    @Transactional
    public void delete(Long id) {
        issueWorklogRepository.delete(id);
    }

    @Transactional
    public void delete(String projectKey, Long idInProject, Long idInIssue) {
        issueWorklogRepository.delete(projectKey, idInProject, idInIssue);
    }

}
