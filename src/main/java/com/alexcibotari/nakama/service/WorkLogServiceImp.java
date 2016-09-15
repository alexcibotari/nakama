package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.domain.WorkLog;
import com.alexcibotari.nakama.repository.WorkLogRepository;
import com.alexcibotari.nakama.service.util.key.IssueKey;
import com.alexcibotari.nakama.service.util.key.KeyUtil;
import com.alexcibotari.nakama.web.rest.resource.WorkLogResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WorkLogServiceImp implements WorkLogService {


    @Autowired
    WorkLogRepository workLogRepository;

    @Autowired
    IssueService issueService;


    public WorkLog findOne(Long id) {
        return workLogRepository.findOne(id);
    }

    public List<WorkLog> findAll() {
        return (List<WorkLog>) workLogRepository.findAll();
    }

    public List<WorkLog> findAllByIssue(String projectKey, Long idInProject) {
        return workLogRepository.findAll(projectKey, idInProject);
    }

    public List<WorkLog> findAllByIssue(String key) {
        IssueKey issueKey = KeyUtil.getIssueKey(key);
        if (issueKey != null && issueKey.isValid()) {
            return findAllByIssue(issueKey.getProjectKey(), issueKey.getIdInProject());
        }
        return null;
    }

    @Transactional
    public WorkLog create(WorkLogResource resource) {
        Issue issue = issueService.findOne(resource.getIssue());
        WorkLog workLog = new WorkLog();
        workLog.setIssue(issue);
        workLog.setContent(resource.getContent());
        workLog.setTimeWorked(resource.getTimeWorked());
        workLog.setStartDate(resource.getStartDate());
        workLog = workLogRepository.save(workLog);
        //Recalculate time spent
        issueService.recalculateTimeSpent(issue);

        return workLog;
    }


    @Transactional
    public WorkLog update(Long id, WorkLogResource resource) {
        WorkLog workLog = findOne(id);
        workLog.setContent(resource.getContent());
        workLog.setTimeWorked(resource.getTimeWorked());
        workLog.setStartDate(resource.getStartDate());

        workLog = workLogRepository.save(workLog);
        //Recalculate time spent
        issueService.recalculateTimeSpent(workLog.getIssue());

        return workLog;
    }

    @Transactional
    public void delete(Long id) {
        WorkLog workLog = workLogRepository.findOne(id);
        workLogRepository.delete(id);
        issueService.recalculateTimeSpent(workLog.getIssue());
    }

    @Transactional
    public void delete(WorkLog workLog){
        workLogRepository.delete(workLog);
        issueService.recalculateTimeSpent(workLog.getIssue());
    }
}
