package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueComment;
import com.alexcibotari.nakama.service.util.key.KeyUtil;

import javax.validation.constraints.Size;

public class IssueCommentDTO extends AbstractAuditingDTO<Long> {

    private String issue;

    @Size(min = 3, max = 250)
    private String content;


    public IssueCommentDTO() {
    }

//    public IssueCommentDTO(Long id, String issue, String content) {
//        this.setId(id);
//        this.setIssue(issue);
//        this.setContent(content);
//    }

    public IssueCommentDTO(IssueComment entity) {
        super(entity);
        this.setId(entity.getIdInIssue());//Override ID
        this.setIssue(entity.getIssue().getKey());
        this.setContent(entity.getContent());

    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public void setIssue(String projectKey, Long idInProject) {
        this.issue = KeyUtil.getIssueKey(projectKey, idInProject);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "IssueCommentDTO{" +
            "id='" + getId() + '\'' +
            ", issue='" + issue + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
