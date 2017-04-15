package com.alexcibotari.nakama.service.util.key;

public class KeyUtil {
    public static String getIssueKey(String projectKey, Long idInProject) {
        return projectKey + "-" + idInProject;
    }

    public static IssueKey getIssueKey(String key){
        return new IssueKey(key);
    }


}
