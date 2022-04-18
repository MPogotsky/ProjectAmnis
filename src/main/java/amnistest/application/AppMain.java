package amnistest.application;

import amnistest.application.GitLab.GitLabClient;
import amnistest.application.GitLab.ParsedCommit;
import org.gitlab4j.api.GitLabApiException;

import java.util.ArrayList;
import java.util.List;

public class AppMain {
    public static void main(String[] args){

        GitLabClient gitLabClient = new GitLabClient();
        List<ParsedCommit> parsedCommits = new ArrayList<ParsedCommit>();

        try{
            parsedCommits = gitLabClient.getCommitList();
            for (ParsedCommit parsedCommit : parsedCommits) {
                parsedCommit.PrintData();
            }
        } catch (GitLabApiException exception){
            exception.getMessage();
        }
    }
}
