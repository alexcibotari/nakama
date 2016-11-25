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
public class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void testFindOneByKey() {
        Assert.assertEquals("TEST", projectRepository.findOneByKey("TEST").get().getKey());
        Assert.assertEquals("BEST", projectRepository.findOneByKey("BEST").get().getKey());
        Assert.assertEquals("BEST1", projectRepository.findOneByKey("BEST1").get().getKey());
        Assert.assertEquals("ESTIMATION", projectRepository.findOneByKey("ESTIMATION").get().getKey());
        Assert.assertFalse(projectRepository.findOneByKey("DEL").isPresent());
    }

    @Test
    public void testDeleteByKey() {
        Assert.assertNotNull(projectRepository.findOneByKey("TEST").get());
        projectRepository.deleteOneByKey("TEST");
        Assert.assertFalse(projectRepository.findOneByKey("TEST").isPresent());
    }
}
