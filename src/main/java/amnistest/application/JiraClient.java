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
        this.jiraObject = new JiraObject();
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

    public void issueDataToJiraObject(){
        List<Issue> issues = getAllUserIssues();
        for (Issue issue: issues){
            System.out.println(issue.getField("customfield_10025").getValue());
        }
    }

    public TimeInStatus getTimeinStatus(){
        //TODO refactor
        //TODO get this to accept Issue
        List<Issue> issues = getAllUserIssues();
        Issue issue = issues.get(1);

        String value = String.valueOf(issue.getField("customfield_10025").getValue());
        String[] split = value.split("\\|");

                System.out.println(Arrays.toString(split));

        String[] split2 = split[0].split("\\_");

        System.out.println(Arrays.toString(split2));
        TimeInStatus tis = new TimeInStatus();
        tis.column = split2[0];
        tis.timesInColumn = Integer.parseInt(split2[2]);
        tis.stayedInColumn = Integer.parseInt(split2[4]);

        //issue.getStatus() gives the column number
        //status.statusCategory.name
        System.out.println(tis.column);
        System.out.println(tis.stayedInColumn);
        System.out.println(tis.timesInColumn);
        return tis;
    }


}
