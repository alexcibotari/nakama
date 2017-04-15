package com.alexcibotari.nakama.service;


import com.alexcibotari.nakama.domain.Comment;
import com.alexcibotari.nakama.domain.Issue;
import com.alexcibotari.nakama.repository.CommentRepository;
import com.alexcibotari.nakama.service.util.key.IssueKey;
import com.alexcibotari.nakama.service.util.key.KeyUtil;
import com.alexcibotari.nakama.web.resource.CommentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentServiceImp implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    IssueService issueService;

    public Optional<Comment> findOne(Long id) {
        return commentRepository.findOneById(id);
    }

    public List<Comment> findAll() {
        return (List<Comment>) commentRepository.findAll();
    }

    public List<Comment> findAllByIssue(String projectKey, Long idInProject) {
        return commentRepository.findAll(projectKey, idInProject);
    }

    public List<Comment> findAllByIssue(String key) {
        IssueKey issueKey = KeyUtil.getIssueKey(key);
        if (issueKey != null && issueKey.isValid()) {
            return findAllByIssue(issueKey.getProjectKey(), issueKey.getIdInProject());
        }
        return null;
    }

    @Transactional
    public Comment create(CommentResource resource) {
        Issue issue = issueService.findOne(resource.getIssue()).get();//TODO to Optional
        Comment comment = new Comment();
        comment.setIssue(issue);
        comment.setContent(resource.getContent());
        return commentRepository.save(comment);
    }


    @Transactional
    public Optional<Comment> update(Long id, CommentResource resource) {
        return findOne(id)
            .map(entity -> {
                entity.setContent(resource.getContent());
                return commentRepository.save(entity);
            });
    }

    @Transactional
    public Optional<Comment> delete(Long id) {
        return commentRepository.deleteOneById(id);
    }

}
