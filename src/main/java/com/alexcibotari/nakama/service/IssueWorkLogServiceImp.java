package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.IssueWorkLog;
import com.alexcibotari.nakama.repository.IssueWorkLogRepository;
import com.alexcibotari.nakama.service.util.key.IssueKey;
import com.alexcibotari.nakama.service.util.key.KeyUtil;
import com.alexcibotari.nakama.web.rest.dto.IssueWorkLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class IssueWorkLogServiceImp implements IssueWorkLogService {


    @Autowired
    IssueWorkLogRepository IssueWorkLogRepository;

    @Autowired
    IssueService issueService;


    public IssueWorkLog findOne(Long id) {
        return IssueWorkLogRepository.findOne(id);
    }

    public IssueWorkLog findOne(String projectKey, Long idInProject, Long idInIssue) {
        return IssueWorkLogRepository.findOne(projectKey, idInProject, idInIssue);
    }

    public List<IssueWorkLog> findAll() {
        return (List<IssueWorkLog>) IssueWorkLogRepository.findAll();
    }

    public List<IssueWorkLog> findAllInIssue(String projectKey, Long idInProject) {
        return IssueWorkLogRepository.findAll(projectKey, idInProject);
    }

    public List<IssueWorkLog> findAllInIssue(String key) {
        IssueKey issueKey = KeyUtil.getIssueKey(key);
        if (issueKey != null && issueKey.isValid()) {
            return findAllInIssue(issueKey.getProjectKey(), issueKey.getIdInProject());
        }
        return null;
    }

    @Transactional
    public IssueWorkLog create(IssueWorkLogDTO dto) {
        IssueWorkLog IssueWorkLog = new IssueWorkLog();
        IssueWorkLog.setIssue(issueService.findOne(dto.getIssue()));
        IssueWorkLog.setContent(dto.getContent());
        return IssueWorkLogRepository.save(IssueWorkLog);
    }


    @Transactional
    public IssueWorkLog update(IssueWorkLogDTO dto) {
        IssueWorkLog IssueWorkLog = findOne(dto.getId());
        IssueWorkLog.setContent(dto.getContent());
        return IssueWorkLogRepository.save(IssueWorkLog);
    }

    @Transactional
    public void delete(Long id) {
        IssueWorkLogRepository.delete(id);
    }

    @Transactional
    public void delete(String projectKey, Long idInProject, Long idInIssue) {
        IssueWorkLog IssueWorkLog = IssueWorkLogRepository.findOne(projectKey, idInProject, idInIssue);
        IssueWorkLogRepository.delete(IssueWorkLog);
    }

}
