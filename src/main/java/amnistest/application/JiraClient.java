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



    public void getIssueComment(){

        //get all fields from issue
        Issue issue1 = restClient.getIssueClient().getIssue("AP-1").claim();
        Iterable<IssueField> fields = issue1.getFields();
        for(IssueField field: fields){
            System.out.println(field.getName() +" : " + field.getId());
        }


        //get all project basic components
        Project project = restClient.getProjectClient().getProject("AP").claim();
        Iterable<BasicComponent> bc =  project.getComponents();

        for (BasicComponent comp : bc) {
            System.out.println(comp.getName() + " : " + comp.getId());
        }



        //getting all comments from issue
        Issue issue = restClient.getIssueClient().getIssue("AP-6").claim();
        Iterable<Comment> commentList =  issue.getComments();
        for (Comment comment : commentList) {
            System.out.println(comment.getBody());
        }
    }

}
