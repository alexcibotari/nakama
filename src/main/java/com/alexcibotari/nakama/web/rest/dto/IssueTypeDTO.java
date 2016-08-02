package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueType;

import javax.validation.constraints.Size;

public class IssueTypeDTO extends AbstractAuditingDTO {

    @Size(min = 3, max = 50)
    private String name;

    @Size(max = 250)
    private String description;

    public IssueTypeDTO() {
    }

    public IssueTypeDTO(Long id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public IssueTypeDTO(IssueType type) {
        this(type.getId(), type.getName(), type.getDescription());
        this.setCreatedDate(type.getCreatedDate());
        this.setLastModifiedDate(type.getLastModifiedDate());
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
        return "IssueTypeDTO{" +
            "id='"+getId() + '\''+
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
