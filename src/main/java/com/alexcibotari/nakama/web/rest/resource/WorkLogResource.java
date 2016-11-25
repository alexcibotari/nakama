package com.alexcibotari.nakama.web.rest.resource;

import com.alexcibotari.nakama.service.util.key.KeyUtil;
import org.springframework.hateoas.core.Relation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Relation(value = "issueWorkLog", collectionRelation = "issueWorkLogs")
public class WorkLogResource extends AbstractAuditingResource {

    @NotNull
    private String issue;

    private String content;

    @NotNull
    @Min(1)
    private Long timeWorked;

    @NotNull
    private ZonedDateTime startDate;

    public WorkLogResource() {
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

    public Long getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(Long timeWorked) {
        this.timeWorked = timeWorked;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "CommentResource{" +
            "id=" + getId() +
            ", issue='" + issue + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
