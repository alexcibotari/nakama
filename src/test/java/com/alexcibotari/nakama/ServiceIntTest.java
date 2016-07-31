package com.alexcibotari.nakama;

import com.alexcibotari.nakama.repository.IssueRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class ServiceIntTest {

    @Autowired
    IssueRepository issueRepository;

    @Test
    public void testNextIssue() {
        System.out.println(issueRepository.getNextInProjectIdByProjectId(1L));
        System.out.println(issueRepository.getNextInProjectIdByProjectId(2L));
        System.out.println(issueRepository.getNextInProjectIdByProjectId(3L));
    }

    @Test
    public void testFindIssueByCompositeKey() {
        System.out.println(issueRepository.findOneByKeys("TEST", 1L));
        System.out.println(issueRepository.findOneByKeys("TEST", 2L));
        System.out.println(issueRepository.findOneByKeys("BEST", 1L));
        System.out.println(issueRepository.findOneByKeys("BEST", 2L));
    }

}
