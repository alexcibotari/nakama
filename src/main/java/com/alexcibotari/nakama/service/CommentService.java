package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Comment;
import com.alexcibotari.nakama.web.rest.resource.CommentResource;

import java.util.List;

public interface CommentService {

    Comment findOne(Long id);

    List<Comment> findAll();

    List<Comment> findAllByIssue(String projectKey, Long idInProject);

    List<Comment> findAllByIssue(String key);

    Comment create(CommentResource resource);

    Comment update(Long id, CommentResource resource);

    void delete(Long id);

}
