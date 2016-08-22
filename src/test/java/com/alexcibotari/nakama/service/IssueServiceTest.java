package com.alexcibotari.nakama.service;

import com.alexcibotari.nakama.Application;
import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.domain.IssueWorkLog;
import com.alexcibotari.nakama.web.rest.dto.IssueWorkLogDTO;
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
    private IssueWorkLogService issueWorkLogService;

    @Test
    public void testRecalculateTimeSpentOnWorkLogDelete() {
        Issue issue = issueService.findOne(ESTIMATION_1);
        Long timeSpent = issue.getTimeSpent();

        IssueWorkLog issueWorkLog = issueWorkLogService.findOne(ESTIMATION_1, 1L);
        timeSpent -= issueWorkLog.getTimeWorked();
        issueWorkLogService.delete(issueWorkLog);

        issue = issueService.findOne(ESTIMATION_1);
        Assert.assertEquals(timeSpent, issue.getTimeSpent());
    }

    @Test
    public void testRecalculateTimeSpentOnWorkLogCreate() {
        Issue issue = issueService.findOne(ESTIMATION_1);
        Long timeSpent = issue.getTimeSpent();

        IssueWorkLogDTO workLogDTO = new IssueWorkLogDTO();
        workLogDTO.setIssue(issue.getKey());
        workLogDTO.setContent("Test");
        workLogDTO.setStartDate(ZonedDateTime.now());
        workLogDTO.setTimeWorked(30L);

        IssueWorkLog issueWorkLog = issueWorkLogService.create(workLogDTO);
        timeSpent += issueWorkLog.getTimeWorked();

        issue = issueService.findOne(ESTIMATION_1);
        Assert.assertEquals(timeSpent, issue.getTimeSpent());
    }

    @Test
    public void testRecalculateTimeSpentOnWorkLogUpdateInPlus() {
        Issue issue = issueService.findOne(ESTIMATION_1);
        Long timeSpent = issue.getTimeSpent();

        IssueWorkLog issueWorkLog = issueWorkLogService.findOne(ESTIMATION_1, 1L);
        IssueWorkLogDTO workLogDTO = new IssueWorkLogDTO(issueWorkLog);
        workLogDTO.setTimeWorked(workLogDTO.getTimeWorked()+30L);
        timeSpent += 30L;
        issueWorkLogService.update(workLogDTO);

        issue = issueService.findOne(ESTIMATION_1);
        Assert.assertEquals(timeSpent, issue.getTimeSpent());
    }

    @Test
    public void testRecalculateTimeSpentOnWorkLogUpdateInMinus() {
        Issue issue = issueService.findOne(ESTIMATION_1);
        Long timeSpent = issue.getTimeSpent();

        IssueWorkLog issueWorkLog = issueWorkLogService.findOne(ESTIMATION_1, 1L);
        IssueWorkLogDTO workLogDTO = new IssueWorkLogDTO(issueWorkLog);
        workLogDTO.setTimeWorked(workLogDTO.getTimeWorked()-30L);
        timeSpent -= 30L;
        issueWorkLogService.update(workLogDTO);

        issue = issueService.findOne(ESTIMATION_1);
        Assert.assertEquals(timeSpent, issue.getTimeSpent());
    }


}
