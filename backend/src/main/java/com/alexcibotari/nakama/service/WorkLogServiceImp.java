package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.domain.WorkLog;
import com.alexcibotari.nakama.repository.WorkLogRepository;
import com.alexcibotari.nakama.service.util.key.IssueKey;
import com.alexcibotari.nakama.service.util.key.KeyUtil;
import com.alexcibotari.nakama.web.resource.WorkLogResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WorkLogServiceImp implements WorkLogService {


    @Autowired
    WorkLogRepository workLogRepository;

    @Autowired
    IssueService issueService;


    public Optional<WorkLog> findOne(Long id) {
        return workLogRepository.findOneById(id);
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
        Issue issue = issueService.findOne(resource.getIssue()).get();//TODO to Optional
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
    public Optional<WorkLog> update(Long id, WorkLogResource resource) {
        return findOne(id)
            .map(entity -> {
                entity.setContent(resource.getContent());
                entity.setTimeWorked(resource.getTimeWorked());
                entity.setStartDate(resource.getStartDate());

                entity = workLogRepository.save(entity);
                //Recalculate time spent
                issueService.recalculateTimeSpent(entity.getIssue());

                return entity;
            });
    }

    @Transactional
    public Optional<WorkLog> delete(Long id) {
        Optional<WorkLog> workLog = workLogRepository.findOneById(id);
        workLog.ifPresent(entity -> {
            workLogRepository.delete(entity);
            issueService.recalculateTimeSpent(entity.getIssue());
        });
        return workLog;
    }
}
