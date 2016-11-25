package com.alexcibotari.nakama.controller;

import com.alexcibotari.nakama.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class ProjectControllerTest {

    private static final String BASE_URI = "http://localhost:8080/api";

    @Test
    public void test() {
        Assert.assertTrue(true);
    }

/*    @Test
    public void createNewProject() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ProjectResource> response = restTemplate.getForEntity(BASE_URI + "/projects/1", ProjectResource.class);
        //assertEquals(HttpStatus.CREATED, response.getStatusCode());
        System.out.println(response);

        // retrieving the newly created author details using the URI received in Location header
*//*        ProjectResource resource = restTemplate.getForObject(response.getHeaders().getLocation(), ProjectResource.class);
        assertEquals(entity.getName(), resource.getName());*//*

        // getting the author's books using the link with rel books
        *//*Link authorBooksLink = author.getLink("books");
        List<BookResource> authorBooks = restTemplate.getForObject(authorBooksLink.getHref(), List.class);
        assertTrue(authorBooks.isEmpty());*//*
    }*/

}
