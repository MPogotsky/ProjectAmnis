package amnistest.application;

public class AppMain {
    public static void main(String[] args){

        JiraClient jiraClient = new JiraClient("256629@student.pwr.edu.pl", "WtRdJblWpMcl2L7eDpyS8198", "https://project-amnis.atlassian.net/");
//        jiraClient.getAllUserIssues();
//        jiraClient.getProjectIssueFields("AP-6");
//        jiraClient.issueDataToJiraObject();
        //jiraClient.getProjectIssueFields("AP-18");
        jiraClient.issueDataToJiraObject();
        int a = jiraClient.jiraObject.calculateTaskValue();
        System.out.println(a);
        //jiraClient.getTimeinStatus();

    }
}
