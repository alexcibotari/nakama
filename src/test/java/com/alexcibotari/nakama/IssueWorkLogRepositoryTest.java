package com.alexcibotari.nakama;

import com.alexcibotari.nakama.repository.IssueWorkLogRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class IssueWorkLogRepositoryTest {

    @Autowired
    IssueWorkLogRepository issueWorkLogRepository;

    @Test
    public void testNextIdInIssueById() {
        Assert.assertEquals(new Long(4L), issueWorkLogRepository.getNextIdInIssue(1L));
        Assert.assertEquals(new Long(3L), issueWorkLogRepository.getNextIdInIssue(2L));
        Assert.assertEquals(new Long(2L), issueWorkLogRepository.getNextIdInIssue(3L));
        Assert.assertEquals(new Long(1L), issueWorkLogRepository.getNextIdInIssue(4L));
    }

    @Test
    public void testNextIdInIssueByKey() {
        Assert.assertEquals(new Long(4L), issueWorkLogRepository.getNextIdInIssue("TEST-1"));
        Assert.assertEquals(new Long(3L), issueWorkLogRepository.getNextIdInIssue("TEST-2"));
        Assert.assertEquals(new Long(2L), issueWorkLogRepository.getNextIdInIssue("BEST-1"));
        Assert.assertEquals(new Long(1L), issueWorkLogRepository.getNextIdInIssue("BEST-2"));
    }

    @Test
    public void testFindAllByIssueId() {
        Assert.assertEquals(3, issueWorkLogRepository.findAll(1L).size());
        Assert.assertEquals(2, issueWorkLogRepository.findAll(2L).size());
        Assert.assertEquals(1, issueWorkLogRepository.findAll(3L).size());
        Assert.assertEquals(0, issueWorkLogRepository.findAll(4L).size());
    }

    @Test
    public void testFindAllByIssueKey() {
        Assert.assertEquals(3, issueWorkLogRepository.findAll("TEST-1").size());
        Assert.assertEquals(2, issueWorkLogRepository.findAll("TEST-2").size());
        Assert.assertEquals(1, issueWorkLogRepository.findAll("BEST-1").size());
        Assert.assertEquals(0, issueWorkLogRepository.findAll("BEST-2").size());
    }

    @Test
    public void testFindAllByProjectKeyAndIdInIssue() {
        Assert.assertEquals(3, issueWorkLogRepository.findAll("TEST", 1L).size());
        Assert.assertEquals(2, issueWorkLogRepository.findAll("TEST", 2L).size());
        Assert.assertEquals(1, issueWorkLogRepository.findAll("BEST", 1L).size());
        Assert.assertEquals(0, issueWorkLogRepository.findAll("BEST", 2L).size());
    }

    @Test
    public void testFindAllByProjectIdAndIdInIssue() {
        Assert.assertEquals(3, issueWorkLogRepository.findAll(1L, 1L).size());
        Assert.assertEquals(2, issueWorkLogRepository.findAll(1L, 2L).size());
        Assert.assertEquals(1, issueWorkLogRepository.findAll(2L, 1L).size());
        Assert.assertEquals(0, issueWorkLogRepository.findAll(2L, 2L).size());
    }

    @Test
    public void testFindOneByIssueKeyAndIdInIssue() {
        Assert.assertEquals("worklog 1", issueWorkLogRepository.findOne("TEST-1", 1L).getContent());
        Assert.assertEquals("worklog 2", issueWorkLogRepository.findOne("TEST-1", 2L).getContent());
        Assert.assertEquals("worklog 3", issueWorkLogRepository.findOne("TEST-1", 3L).getContent());
        Assert.assertEquals("worklog 4", issueWorkLogRepository.findOne("TEST-2", 1L).getContent());
        Assert.assertEquals("worklog 5", issueWorkLogRepository.findOne("TEST-2", 2L).getContent());
        Assert.assertEquals("worklog 6", issueWorkLogRepository.findOne("BEST-1", 1L).getContent());
    }

    @Test
    public void testFindOneByProjectKeyAndIdInProjectAndIdInIssue() {
        Assert.assertEquals("worklog 1", issueWorkLogRepository.findOne("TEST", 1L, 1L).getContent());
        Assert.assertEquals("worklog 2", issueWorkLogRepository.findOne("TEST", 1L, 2L).getContent());
        Assert.assertEquals("worklog 3", issueWorkLogRepository.findOne("TEST", 1L, 3L).getContent());
        Assert.assertEquals("worklog 4", issueWorkLogRepository.findOne("TEST", 2L, 1L).getContent());
        Assert.assertEquals("worklog 5", issueWorkLogRepository.findOne("TEST", 2L, 2L).getContent());
        Assert.assertEquals("worklog 6", issueWorkLogRepository.findOne("BEST", 1L, 1L).getContent());
    }

    @Test
    public void testFindOneByProjectIdAndIdInProjectAndIdInIssue() {
        Assert.assertEquals("worklog 1", issueWorkLogRepository.findOne(1L, 1L, 1L).getContent());
        Assert.assertEquals("worklog 2", issueWorkLogRepository.findOne(1L, 1L, 2L).getContent());
        Assert.assertEquals("worklog 3", issueWorkLogRepository.findOne(1L, 1L, 3L).getContent());
        Assert.assertEquals("worklog 4", issueWorkLogRepository.findOne(1L, 2L, 1L).getContent());
        Assert.assertEquals("worklog 5", issueWorkLogRepository.findOne(1L, 2L, 2L).getContent());
        Assert.assertEquals("worklog 6", issueWorkLogRepository.findOne(2L, 1L, 1L).getContent());
    }


}
