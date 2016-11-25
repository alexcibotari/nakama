package com.alexcibotari.nakama.service.util.key;

public class IssueKey {
    private String projectKey;
    private Long idInProject;
    private boolean valid = false;

    public IssueKey() {
    }

    public IssueKey(String key) {
        if (key == null || key.length() < 3) {
            return;
        }
        String[] keyArr = key.split("-");
        if (keyArr == null || keyArr.length < 2 || keyArr.length > 2) {
            return;
        }
        projectKey = keyArr[0];
        idInProject = Long.parseLong(keyArr[1]);
        valid = true;
    }

    public IssueKey(String projectKey, Long idInProject) {
        this.projectKey = projectKey;
        this.idInProject = idInProject;
        this.valid = true;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public Long getIdInProject() {
        return idInProject;
    }

    public boolean isValid() {
        return valid;
    }
}
