package amnistest.application;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.OptionalIterable;
import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.api.domain.input.AuditRecordSearchInput;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import io.atlassian.util.concurrent.Promise;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JiraClient {
     public String username;
     public String password;
     public String jiraUrl;
     public JiraRestClient restClient;
     public JiraObject jiraObject;


    public JiraClient(String username, String password, String jiraUrl){
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestClient();
        this.jiraObject = new JiraObject(this);
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
     * @param key Issue Key
     *
     */
    public void getIssueComment(String key){
        Issue issue = restClient.getIssueClient().getIssue(key).claim();
        Iterable<Comment> commentList =  issue.getComments();
        for (Comment comment : commentList) {
            System.out.println(comment.getBody());
        }

    }

    /**
     * Getting project URI
     *
     * @param projectKey Project Key
     */
    public void getProjectURI(String projectKey){
        Project project = restClient.getProjectClient().getProject(projectKey).claim();
        System.out.println(project.getUri());
    }

    public List<String> getAllUserProjects(){
        Iterable<BasicProject> projects = restClient.getProjectClient().getAllProjects().claim();
        List<String> userProjects = new ArrayList<String>();
        for (BasicProject proj: projects){
            userProjects.add(proj.getKey());
        }
        return userProjects;
    }

    public List<Issue> getAllUserIssues(){
        List<String> userProjectNames = getAllUserProjects();
        List<Issue> userIssues = new ArrayList<Issue>();
        for (String projectName : userProjectNames){
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
    public void getProjectIssueFields(String key){
        Issue issue1 = restClient.getIssueClient().getIssue(key).claim();
        Iterable<IssueField> fields = issue1.getFields();
        for(IssueField field: fields){
            System.out.println(field.getName() +" : " + field.getValue());
        }
    }

    public String getFieldValue(String IssueKey, String fieldID){
        Issue issue1 = restClient.getIssueClient().getIssue(IssueKey).claim();
        IssueField field = issue1.getField(fieldID);
        return String.valueOf(field.getValue());
    }



    public void issueDataToJiraObject(){
        this.jiraObject = new JiraObject(this);
        this.jiraObject.print();
    }

    public List<TimeInStatus> getTimeinStatus(String IssueKey){
        List<TimeInStatus> timeInStatuses = new ArrayList<TimeInStatus>();
        Issue issue = restClient.getIssueClient().getIssue(IssueKey).claim();

        String value = String.valueOf(issue.getField("customfield_10025").getValue());
        String[] columns = value.split("\\|");
        for (String column : columns){
            String[] separatedValues = column.split("\\_");
            TimeInStatus tis = getTimeInStatus(separatedValues);
            timeInStatuses.add(tis);
        }
        return timeInStatuses;
    }

    public String extractColumnName(String[] data){
        if(data.length == 6){
            if(!data[0].equals("*")){
                return data[0];
            }
            else return data[1];

        }
        return data[1];
    }

    public int extractTimeInColumn(String[] data){
        if(data.length == 6){
            if(!data[0].equals("*")){
                return Integer.parseInt(data[2]);
            }
            else {
                return Integer.parseInt(data[3]);
            }
        }
        return Integer.parseInt(data[3]);

    }

    public int extractStayedInColumn(String[] data){
        if(data.length == 6){
            if(!data[0].equals("*")){
                return Integer.parseInt(data[4]);
            }
            else {
                return Integer.parseInt(data[5]);
            }
        }
        return Integer.parseInt(data[5]);

    }

    public TimeInStatus getTimeInStatus(String[] data){
        TimeInStatus tis = new TimeInStatus();
        tis.column = extractColumnName(data);
        tis.timesInColumn = extractTimeInColumn(data);
        tis.stayedInColumn = extractStayedInColumn(data);
        return tis;
    }


}
