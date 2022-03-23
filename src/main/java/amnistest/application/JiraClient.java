package amnistest.application;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.api.domain.input.AuditRecordSearchInput;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import io.atlassian.util.concurrent.Promise;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class JiraClient {
     public String username;
     public String password;
     public String jiraUrl;
     public JiraRestClient restClient;


    public JiraClient(String username, String password, String jiraUrl){
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
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

    /**
     * Getting all project issue fields from
     *
     * @param key Issue Key
     */
    public void getProjectIssueFields(String key){
        Issue issue1 = restClient.getIssueClient().getIssue(key).claim();
        Iterable<IssueField> fields = issue1.getFields();
        for(IssueField field: fields){
            System.out.println(field.getName() +" : " + field.getId());
        }
    }


}
