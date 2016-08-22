package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
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
    IssueWorkLogRepository issueWorkLogRepository;

    @Autowired
    IssueService issueService;


    public IssueWorkLog findOne(Long id) {
        return issueWorkLogRepository.findOne(id);
    }

    public IssueWorkLog findOne(String projectKey, Long idInProject, Long idInIssue) {
        return issueWorkLogRepository.findOne(projectKey, idInProject, idInIssue);
    }

    public IssueWorkLog findOne(String issueKey, Long idInIssue) {
        return issueWorkLogRepository.findOne(issueKey, idInIssue);
    }

    public List<IssueWorkLog> findAll() {
        return (List<IssueWorkLog>) issueWorkLogRepository.findAll();
    }

    public List<IssueWorkLog> findAllInIssue(String projectKey, Long idInProject) {
        return issueWorkLogRepository.findAll(projectKey, idInProject);
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
        Issue issue = issueService.findOne(dto.getIssue());
        IssueWorkLog issueWorkLog = new IssueWorkLog();
        issueWorkLog.setIssue(issue);
        issueWorkLog.setContent(dto.getContent());
        issueWorkLog.setTimeWorked(dto.getTimeWorked());
        issueWorkLog.setStartDate(dto.getStartDate());
        issueWorkLog.setIdInIssue(issueWorkLogRepository.getNextIdInIssue(issue.getId()));

        issueWorkLog = issueWorkLogRepository.save(issueWorkLog);
        //Recalculate time spent
        issueService.recalculateTimeSpent(issue);

        return issueWorkLog;
    }


    @Transactional
    public IssueWorkLog update(IssueWorkLogDTO dto) {
        IssueWorkLog issueWorkLog = findOne(dto.getIssue(), dto.getId());
        issueWorkLog.setContent(dto.getContent());
        issueWorkLog.setTimeWorked(dto.getTimeWorked());
        issueWorkLog.setStartDate(dto.getStartDate());

        issueWorkLog = issueWorkLogRepository.save(issueWorkLog);
        //Recalculate time spent
        issueService.recalculateTimeSpent(issueWorkLog.getIssue());

        return issueWorkLog;
    }

    @Transactional
    public void delete(Long id) {
        IssueWorkLog issueWorkLog = issueWorkLogRepository.findOne(id);
        issueWorkLogRepository.delete(id);
        issueService.recalculateTimeSpent(issueWorkLog.getIssue());
    }

    @Transactional
    public void delete(IssueWorkLog issueWorkLog){
        issueWorkLogRepository.delete(issueWorkLog);
        issueService.recalculateTimeSpent(issueWorkLog.getIssue());
    }

    @Transactional
    public void delete(String issueKey, Long idInIssue){
        IssueWorkLog issueWorkLog = issueWorkLogRepository.findOne(issueKey,idInIssue);
        issueWorkLogRepository.delete(issueWorkLog);
        issueService.recalculateTimeSpent(issueWorkLog.getIssue());
    }

    @Transactional
    public void delete(String projectKey, Long idInProject, Long idInIssue) {
        IssueWorkLog issueWorkLog = issueWorkLogRepository.findOne(projectKey, idInProject, idInIssue);
        issueWorkLogRepository.delete(issueWorkLog);
        issueService.recalculateTimeSpent(issueWorkLog.getIssue());
    }

}
