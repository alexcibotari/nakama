package com.alexcibotari.nakama;

import com.alexcibotari.nakama.repository.IssueRepository;
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
public class IssueRepositoryTest {

    @Autowired
    IssueRepository issueRepository;

    @Test
    public void testNextIdInProjectById() {
        Assert.assertEquals(new Long(3L), issueRepository.getNextIdInProject(1L));
        Assert.assertEquals(new Long(4L), issueRepository.getNextIdInProject(2L));
        Assert.assertEquals(new Long(1L), issueRepository.getNextIdInProject(3L));
    }

    @Test
    public void testNextIdInProjectByKey() {
        Assert.assertEquals(new Long(3L), issueRepository.getNextIdInProject("TEST"));
        Assert.assertEquals(new Long(4L), issueRepository.getNextIdInProject("BEST"));
        Assert.assertEquals(new Long(1L), issueRepository.getNextIdInProject("BEST1"));
    }

    @Test
    public void testFindAllProjectId() {
        Assert.assertEquals(1, issueRepository.findAllByProjectId(1L).size());
        Assert.assertEquals(3, issueRepository.findAllByProjectId(2L).size());
        Assert.assertEquals(0, issueRepository.findAllByProjectId(3L).size());
    }

    @Test
    public void testFindAllProjectKey() {
        Assert.assertEquals(1, issueRepository.findAllByProjectKey("TEST").size());
        Assert.assertEquals(3, issueRepository.findAllByProjectKey("BEST").size());
        Assert.assertEquals(0, issueRepository.findAllByProjectKey("BEST1").size());
    }

    @Test
    public void testFindOneByKey() {
        Assert.assertEquals("TEST-1", issueRepository.findOne("TEST-1").getKey());
        Assert.assertNull(issueRepository.findOne("TEST-2"));
        Assert.assertEquals("BEST-1", issueRepository.findOne("BEST-1").getKey());
        Assert.assertEquals("BEST-2", issueRepository.findOne("BEST-2").getKey());
        Assert.assertEquals("BEST-3", issueRepository.findOne("BEST-3").getKey());
    }

    @Test
    public void testFindOneByProjectKeyAndIdInIssue() {
        Assert.assertEquals("TEST-1", issueRepository.findOne("TEST", 1L).getKey());
        Assert.assertNull(issueRepository.findOne("TEST", 2L));
        Assert.assertEquals("BEST-1", issueRepository.findOne("BEST", 1L).getKey());
        Assert.assertEquals("BEST-2", issueRepository.findOne("BEST", 2L).getKey());
        Assert.assertEquals("BEST-3", issueRepository.findOne("BEST", 3L).getKey());
    }

    @Test
    public void testFindOneByProjectIdAndIdInIssue() {
        Assert.assertEquals("TEST-1", issueRepository.findOne(1L, 1L).getKey());
        Assert.assertNull(issueRepository.findOne(1L, 2L));
        Assert.assertEquals("BEST-1", issueRepository.findOne(2L, 1L).getKey());
        Assert.assertEquals("BEST-2", issueRepository.findOne(2L, 2L).getKey());
        Assert.assertEquals("BEST-3", issueRepository.findOne(2L, 3L).getKey());
    }


}
