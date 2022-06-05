package com.amnis.amnisapi;

import com.amnis.model.*;
import com.atlassian.jira.rest.client.api.StatusCategory;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.json.JsonParseUtil;

import java.util.ArrayList;
import java.util.List;

import static com.amnis.amnisapi.TaskStatuses.*;

public class JiraConnector {
    private UsersEntity user;
    private CrudDAO databaseHandler;

    public JiraConnector(UsersEntity user, CrudDAO databaseHandler) {
        this.user = user;
        this.databaseHandler = databaseHandler;
    }

    public TasksEntity requestData(String taskId, String companyURI) {
        JiraClient jiraClient = new JiraClient(user.getLogin(), user.getToken(), companyURI);
        Issue issue = jiraClient.getIssue(taskId);
        JiraObject jiraObject = new JiraObject(jiraClient, taskId);
        System.out.println(issue.getStatus().getName());
        switch (issue.getStatus().getName()) {
            case "DONE" -> {
                System.out.println(jiraObject.calculateTaskValue().toString());
                return new TasksEntity(user.getId(), COMPLETE.statusValue,
                        jiraObject.calculateOverallTime(), Math.toIntExact(jiraObject.calculateTaskValue()), taskId);
            }
            case "TO DO" -> {
                return new TasksEntity(user.getId(), TODO.statusValue, null,
                        null, taskId);
            }
            case "In Progress" -> {
                return new TasksEntity(user.getId(), INPROGRESS.statusValue, jiraObject.calculateOverallTime(), null, taskId);
            }
            default -> {
                return new TasksEntity(user.getId(), UNDEFINED.statusValue, null, null, taskId);
            }
        }
    }
}
