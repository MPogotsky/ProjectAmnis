package com.amnis.amnisapi;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import io.atlassian.util.concurrent.Promise;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class JiraClient {
    private String username;
    private String password;
    private String jiraUrl;
    private JiraRestClient restClient;

    public JiraClient(String username, String password, String jiraUrl) {
        setUsername(username);
        setPassword(password);
        setJiraUrl(jiraUrl);
        this.restClient = getJiraRestClient();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }


    /**
     * Getting all comments from issue
     * <p>
     *
     * @param key Issue Key
     */
    public void getIssueComment(String key) {
        Issue issue = restClient.getIssueClient().getIssue(key).claim();
        Iterable<Comment> commentList = issue.getComments();
        for (Comment comment : commentList) {
            System.out.println(comment.getBody());
        }

    }

    /**
     * Getting project URI
     *
     * @param projectKey Project Key
     */
    public void getProjectURI(String projectKey) {
        Project project = restClient.getProjectClient().getProject(projectKey).claim();
        System.out.println(project.getUri());
    }

    /**
     * Creates list of all user projects by keys from JiraClient user
     *
     */
    public List<String> getAllUserProjects() {
        Iterable<BasicProject> projects = restClient.getProjectClient().getAllProjects().claim();
        List<String> userProjects = new ArrayList<>();
        for (BasicProject proj : projects) {
            userProjects.add(proj.getKey());
        }
        return userProjects;
    }

    public Issue getIssue(String issueKey){
        return restClient.getIssueClient().getIssue(issueKey).claim();
    }

    /**
     * Creates list of all user issues
     * from all projects assigned to user
     *
     */
    public List<Issue> getAllUserIssues() {
        List<String> userProjectNames = getAllUserProjects();
        List<Issue> userIssues = new ArrayList<Issue>();
        for (String projectName : userProjectNames) {
            Project project = restClient.getProjectClient().getProject(projectName).claim();
            Promise<SearchResult> searchJqlPromise = restClient.getSearchClient().searchJql("project =" + project.getName() + " AND assignee=currentUser()");
            for (Issue issue : searchJqlPromise.claim().getIssues()) {
                userIssues.add(issue);
            }
        }
        return userIssues;
    }

    /**
     * Getting all project issue fields from
     *
     * @param key Issue Key
     */
    public void getProjectIssueFields(String key) {
        Issue issue1 = restClient.getIssueClient().getIssue(key).claim();
        Iterable<IssueField> fields = issue1.getFields();
        for (IssueField field : fields) {
            System.out.println(field.getName() + " : " + field.getValue());
            System.out.println(field.getName() + " : " + field.getId());
        }
    }

    public String getFieldValue(String IssueKey, String fieldID) {
        IssueField field = getIssue(IssueKey).getField(fieldID);
        return String.valueOf(field.getValue());
    }



    public List<TimeInStatus> getTimesInStatuses(String IssueKey) {
        List<TimeInStatus> timeInStatuses = new ArrayList<>();
        Issue issue = getIssue(IssueKey);

        String value = String.valueOf(issue.getField(FieldID.TIME_IN_STATUS.id).getValue());
        if (!value.equals("null")) {
            String[] columns = value.split("\\|");
            for (String column : columns) {
                String[] separatedValues = column.split("\\_");
                TimeInStatus tis = getTimeInStatus(separatedValues);
                timeInStatuses.add(tis);
            }
        }
        return timeInStatuses;
    }

    public String extractColumnName(String[] data) {
        if (data.length == 6) {
            if (!data[0].equals("*")) {
                return data[0];
            } else return data[1];

        }
        return data[1];
    }

    public int extractTimeInColumn(String[] data) {
        if (data.length == 6) {
            if (!data[0].equals("*")) {
                return Integer.parseInt(data[2]);
            } else {
                return Integer.parseInt(data[3]);
            }
        }
        return Integer.parseInt(data[3]);

    }

    public int extractStayedInColumn(String[] data) {
        if (data.length == 6) {
            if (!data[0].equals("*")) {
                return Integer.parseInt(data[4]);
            } else {
                return Integer.parseInt(data[5]);
            }
        }
        return Integer.parseInt(data[5]);

    }

    public TimeInStatus getTimeInStatus(String[] data) {
        TimeInStatus tis = new TimeInStatus();
        tis.column = extractColumnName(data);
        tis.timesInColumn = extractTimeInColumn(data);
        tis.stayedInColumn = extractStayedInColumn(data);
        return tis;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJiraUrl() {
        return jiraUrl;
    }

    public void setJiraUrl(String jiraUrl) {
        this.jiraUrl = jiraUrl;
    }

    public JiraRestClient getRestClient() {
        return restClient;
    }

    public void setRestClient(JiraRestClient restClient) {
        this.restClient = restClient;
    }

}
