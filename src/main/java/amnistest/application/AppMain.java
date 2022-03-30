package amnistest.application;

import org.gitlab4j.api.GitLabApiException;

public class AppMain {
    public static void main(String[] args){

        JiraClient jiraClient = new JiraClient("256629@student.pwr.edu.pl", "WtRdJblWpMcl2L7eDpyS8198", "https://project-amnis.atlassian.net/");

        jiraClient.getIssueComment("AP-6");
        jiraClient.getProjectURI("AP");
        jiraClient.getProjectIssueFields("AP-1");


        GitLabClient gitLabClient = new GitLabClient();
        try{
            gitLabClient.getCommitHistory();
            gitLabClient.getUser();
        } catch (GitLabApiException exception){
            exception.getMessage();
        }

    }
}
