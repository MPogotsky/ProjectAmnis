package amnistest.application;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.net.URI;
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


    //getting all comments from issue
    public void getIssueComment(){
        Issue issue = restClient.getIssueClient().getIssue("AP-6").claim();
        Iterable<Comment> commentList =  issue.getComments();
        for (Comment comment : commentList) {
            System.out.println(comment.getBody());
        }
    }

}
