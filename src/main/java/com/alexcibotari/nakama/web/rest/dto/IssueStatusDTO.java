package com.alexcibotari.nakama.web.rest.dto;

import com.alexcibotari.nakama.domain.IssueStatus;

import javax.validation.constraints.Size;

public class IssueStatusDTO extends AbstractAuditingDTO<Long> {

    @Size(min = 3, max = 50)
    private String name;

    @Size(max = 250)
    private String description;

    public IssueStatusDTO() {
    }

//    public IssueStatusDTO(Long id, String name, String description) {
//        this.setId(id);
//        this.setName(name);
//        this.setDescription(description);
//    }

    public IssueStatusDTO(IssueStatus entity) {
        super(entity);
        this.setName(entity.getName());
        this.setDescription(entity.getDescription());
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
        return "IssueStatusDTO{" +
            "id='" + getId() + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
