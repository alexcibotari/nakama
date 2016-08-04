package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.domain.IssueComment;
import com.alexcibotari.nakama.repository.IssueCommentRepository;
import com.alexcibotari.nakama.service.util.key.IssueKey;
import com.alexcibotari.nakama.service.util.key.KeyUtil;
import com.alexcibotari.nakama.web.rest.dto.IssueCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IssueCommentServiceImp implements IssueCommentService {


    @Autowired
    IssueCommentRepository issueCommentRepository;

    @Autowired
    IssueService issueService;


    public IssueComment findOne(Long id) {
        return issueCommentRepository.findOne(id);
    }

    @Override
    public IssueComment findOne(String projectKey, Long idInProject, Long idInIssue) {
        return issueCommentRepository.findOne(projectKey, idInProject, idInIssue);
    }

    public List<IssueComment> findAll() {
        return (List<IssueComment>) issueCommentRepository.findAll();
    }

    public List<IssueComment> findAllInIssue(String projectKey, Long idInProject) {
        return issueCommentRepository.findAll(projectKey, idInProject);
    }

    public List<IssueComment> findAllInIssue(String key) {
        IssueKey issueKey = KeyUtil.getIssueKey(key);
        if (issueKey != null && issueKey.isValid()) {
            return findAllInIssue(issueKey.getProjectKey(), issueKey.getIdInProject());
        }
        return null;
    }

    @Transactional
    public IssueComment create(IssueCommentDTO dto) {
        Issue issue = issueService.findOne(dto.getIssue());
        IssueComment issueComment = new IssueComment();
        issueComment.setIssue(issue);
        issueComment.setContent(dto.getContent());
        issueComment.setIdInIssue(issueCommentRepository.getNextIdInIssue(issue.getId()));
        return issueCommentRepository.save(issueComment);
    }


    @Transactional
    public IssueComment update(IssueCommentDTO dto) {
        IssueComment issueComment = findOne(dto.getId());
        issueComment.setContent(dto.getContent());
        return issueCommentRepository.save(issueComment);
    }

    @Transactional
    public void delete(Long id) {
        issueCommentRepository.delete(id);
    }

    @Transactional
    public void delete(String projectKey, Long idInProject, Long idInIssue) {
        IssueComment issueComment = issueCommentRepository.findOne(projectKey, idInProject, idInIssue);
        issueCommentRepository.delete(issueComment);
    }

}
