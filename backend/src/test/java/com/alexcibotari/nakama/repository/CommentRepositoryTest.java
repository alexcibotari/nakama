package com.alexcibotari.nakama.repository;

import com.alexcibotari.nakama.Application;
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
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void testFindAllByIssueId() {
        Assert.assertEquals(3, commentRepository.findAll(1L).size());
        Assert.assertEquals(2, commentRepository.findAll(2L).size());
        Assert.assertEquals(1, commentRepository.findAll(3L).size());
        Assert.assertEquals(0, commentRepository.findAll(4L).size());
    }

    @Test
    public void testFindAllByIssueKey() {
        Assert.assertEquals(3, commentRepository.findAll("TEST-1").size());
        Assert.assertEquals(2, commentRepository.findAll("TEST-2").size());
        Assert.assertEquals(1, commentRepository.findAll("BEST-1").size());
        Assert.assertEquals(0, commentRepository.findAll("BEST-2").size());
    }

    @Test
    public void testFindAllByProjectKeyAndIdInIssue() {
        Assert.assertEquals(3, commentRepository.findAll("TEST", 1L).size());
        Assert.assertEquals(2, commentRepository.findAll("TEST", 2L).size());
        Assert.assertEquals(1, commentRepository.findAll("BEST", 1L).size());
        Assert.assertEquals(0, commentRepository.findAll("BEST", 2L).size());
    }

    @Test
    public void testFindAllByProjectIdAndIdInIssue() {
        Assert.assertEquals(3, commentRepository.findAll(1L, 1L).size());
        Assert.assertEquals(2, commentRepository.findAll(1L, 2L).size());
        Assert.assertEquals(1, commentRepository.findAll(2L, 1L).size());
        Assert.assertEquals(0, commentRepository.findAll(2L, 2L).size());
    }

}
