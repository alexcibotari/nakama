package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssuePriority;

import javax.validation.constraints.Size;

public class IssuePriorityDTO extends AbstractAuditingDTO {

    @Size(min = 3, max = 50)
    private String name;

    @Size(max = 250)
    private String description;

    public IssuePriorityDTO() {
    }

    public IssuePriorityDTO(Long id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public IssuePriorityDTO(IssuePriority priority) {
        this(priority.getId(), priority.getName(), priority.getDescription());
        this.setCreatedDate(priority.getCreatedDate());
        this.setLastModifiedDate(priority.getLastModifiedDate());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "IssuePriorityDTO{" +
            "id='"+getId() + '\''+
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
