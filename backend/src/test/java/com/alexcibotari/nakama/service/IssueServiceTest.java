package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.Application;
import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.domain.WorkLog;
import com.alexcibotari.nakama.web.assembler.WorkLogResourceAssembler;
import com.alexcibotari.nakama.web.resource.WorkLogResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class IssueServiceTest {

    private final String ESTIMATION_1 = "ESTIMATION-1";

    @Autowired
    private IssueService issueService;

    @Autowired
    private WorkLogService workLogService;

    @Autowired
    private WorkLogResourceAssembler workLogResourceAssembler;

    @Test
    public void testRecalculateTimeSpentOnWorkLogDelete() {
        Issue issue = issueService.findOne(ESTIMATION_1).get();
        Long timeSpent = issue.getTimeSpent();

        WorkLog workLog = workLogService.findAllByIssue(ESTIMATION_1).get(0);
        timeSpent -= workLog.getTimeWorked();
        //workLogService.delete(workLog.getId());

        issue = issueService.findOne(ESTIMATION_1).get();
        Assert.assertEquals(timeSpent, issue.getTimeSpent());
    }

    @Test
    public void testRecalculateTimeSpentOnWorkLogCreate() {
        Issue issue = issueService.findOne(ESTIMATION_1).get();
        Long timeSpent = issue.getTimeSpent();

        WorkLogResource workLogDTO = new WorkLogResource();
        workLogDTO.setIssue(issue.getKey());
        workLogDTO.setContent("Test");
        workLogDTO.setStartDate(ZonedDateTime.now());
        workLogDTO.setTimeWorked(30L);

        WorkLog workLog = workLogService.create(workLogDTO);
        timeSpent += workLog.getTimeWorked();

        issue = issueService.findOne(ESTIMATION_1).get();
        Assert.assertEquals(timeSpent, issue.getTimeSpent());
    }

    @Test
    public void testRecalculateTimeSpentOnWorkLogUpdateInPlus() {
        Issue issue = issueService.findOne(ESTIMATION_1).get();
        Long timeSpent = issue.getTimeSpent();

        WorkLog workLog = workLogService.findAllByIssue(ESTIMATION_1).get(0);
        WorkLogResource workLogDTO = workLogResourceAssembler.toResource(workLog);
        workLogDTO.setTimeWorked(workLogDTO.getTimeWorked() + 30L);
        timeSpent += 30L;
        //workLogService.update(workLog.getId(), workLogDTO);

        issue = issueService.findOne(ESTIMATION_1).get();
        Assert.assertEquals(timeSpent, issue.getTimeSpent());
    }

    @Test
    public void testRecalculateTimeSpentOnWorkLogUpdateInMinus() {
        Issue issue = issueService.findOne(ESTIMATION_1).get();
        Long timeSpent = issue.getTimeSpent();

        WorkLog workLog = workLogService.findAllByIssue(ESTIMATION_1).get(0);
        WorkLogResource workLogDTO = workLogResourceAssembler.toResource(workLog);
        workLogDTO.setTimeWorked(workLogDTO.getTimeWorked() - 30L);
        timeSpent -= 30L;
        //workLogService.update(workLog.getId(), workLogDTO);

        issue = issueService.findOne(ESTIMATION_1).get();
        Assert.assertEquals(timeSpent, issue.getTimeSpent());
    }


}
