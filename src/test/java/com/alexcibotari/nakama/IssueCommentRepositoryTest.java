package com.alexcibotari.nakama;

import com.alexcibotari.nakama.repository.IssueCommentRepository;
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
public class IssueCommentRepositoryTest {

    @Autowired
    IssueCommentRepository issueCommentRepository;

    @Test
    public void testNextIdInIssueById() {
        Assert.assertEquals(new Long(4L), issueCommentRepository.getNextIdInIssue(1L));
        Assert.assertEquals(new Long(3L), issueCommentRepository.getNextIdInIssue(2L));
        Assert.assertEquals(new Long(2L), issueCommentRepository.getNextIdInIssue(3L));
        Assert.assertEquals(new Long(1L), issueCommentRepository.getNextIdInIssue(4L));
    }

    @Test
    public void testNextIdInIssueByKey() {
        Assert.assertEquals(new Long(4L), issueCommentRepository.getNextIdInIssue("TEST-1"));
        Assert.assertEquals(new Long(3L), issueCommentRepository.getNextIdInIssue("TEST-2"));
        Assert.assertEquals(new Long(2L), issueCommentRepository.getNextIdInIssue("BEST-1"));
        Assert.assertEquals(new Long(1L), issueCommentRepository.getNextIdInIssue("BEST-2"));
    }

    @Test
    public void testFindAllByIssueId() {
        Assert.assertEquals(3, issueCommentRepository.findAll(1L).size());
        Assert.assertEquals(2, issueCommentRepository.findAll(2L).size());
        Assert.assertEquals(1, issueCommentRepository.findAll(3L).size());
        Assert.assertEquals(0, issueCommentRepository.findAll(4L).size());
    }

    @Test
    public void testFindAllByIssueKey() {
        Assert.assertEquals(3, issueCommentRepository.findAll("TEST-1").size());
        Assert.assertEquals(2, issueCommentRepository.findAll("TEST-2").size());
        Assert.assertEquals(1, issueCommentRepository.findAll("BEST-1").size());
        Assert.assertEquals(0, issueCommentRepository.findAll("BEST-2").size());
    }

    @Test
    public void testFindAllByProjectKeyAndIdInIssue() {
        Assert.assertEquals(3, issueCommentRepository.findAll("TEST", 1L).size());
        Assert.assertEquals(2, issueCommentRepository.findAll("TEST", 2L).size());
        Assert.assertEquals(1, issueCommentRepository.findAll("BEST", 1L).size());
        Assert.assertEquals(0, issueCommentRepository.findAll("BEST", 2L).size());
    }

    @Test
    public void testFindAllByProjectIdAndIdInIssue() {
        Assert.assertEquals(3, issueCommentRepository.findAll(1L, 1L).size());
        Assert.assertEquals(2, issueCommentRepository.findAll(1L, 2L).size());
        Assert.assertEquals(1, issueCommentRepository.findAll(2L, 1L).size());
        Assert.assertEquals(0, issueCommentRepository.findAll(2L, 2L).size());
    }

    @Test
    public void testFindOneByIssueKeyAndIdInIssue() {
        Assert.assertEquals("comment 1", issueCommentRepository.findOne("TEST-1", 1L).getContent());
        Assert.assertEquals("comment 2", issueCommentRepository.findOne("TEST-1", 2L).getContent());
        Assert.assertEquals("comment 3", issueCommentRepository.findOne("TEST-1", 3L).getContent());
        Assert.assertEquals("comment 4", issueCommentRepository.findOne("TEST-2", 1L).getContent());
        Assert.assertEquals("comment 5", issueCommentRepository.findOne("TEST-2", 2L).getContent());
        Assert.assertEquals("comment 6", issueCommentRepository.findOne("BEST-1", 1L).getContent());
    }

    @Test
    public void testFindOneByProjectKeyAndIdInProjectAndIdInIssue() {
        Assert.assertEquals("comment 1", issueCommentRepository.findOne("TEST", 1L, 1L).getContent());
        Assert.assertEquals("comment 2", issueCommentRepository.findOne("TEST", 1L, 2L).getContent());
        Assert.assertEquals("comment 3", issueCommentRepository.findOne("TEST", 1L, 3L).getContent());
        Assert.assertEquals("comment 4", issueCommentRepository.findOne("TEST", 2L, 1L).getContent());
        Assert.assertEquals("comment 5", issueCommentRepository.findOne("TEST", 2L, 2L).getContent());
        Assert.assertEquals("comment 6", issueCommentRepository.findOne("BEST", 1L, 1L).getContent());
    }

    @Test
    public void testFindOneByProjectIdAndIdInProjectAndIdInIssue() {
        Assert.assertEquals("comment 1", issueCommentRepository.findOne(1L, 1L, 1L).getContent());
        Assert.assertEquals("comment 2", issueCommentRepository.findOne(1L, 1L, 2L).getContent());
        Assert.assertEquals("comment 3", issueCommentRepository.findOne(1L, 1L, 3L).getContent());
        Assert.assertEquals("comment 4", issueCommentRepository.findOne(1L, 2L, 1L).getContent());
        Assert.assertEquals("comment 5", issueCommentRepository.findOne(1L, 2L, 2L).getContent());
        Assert.assertEquals("comment 6", issueCommentRepository.findOne(2L, 1L, 1L).getContent());
    }


}
