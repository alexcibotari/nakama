package com.alexcibotari.nakama.web.rest.resource;

import com.alexcibotari.nakama.service.util.key.KeyUtil;
import org.springframework.hateoas.core.Relation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Relation(value = "issueComment", collectionRelation = "issueComments")
public class CommentResource extends AbstractAuditingResource {

    @NotNull
    private String issue;

    @Size(min = 3, max = 250)
    private String content;


    public CommentResource() {
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
        return "CommentResource{" +
            "id='" + getId() + '\'' +
            ", issue='" + issue + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
