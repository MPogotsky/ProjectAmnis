package com.amnis.amnisapi;

import com.amnis.model.CrudDAO;
import com.amnis.model.TasksEntity;
import com.amnis.model.UsersEntity;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;

import java.util.ArrayList;
import java.util.List;

public class GitlabConnector {

    UsersEntity user;
    CrudDAO databaseHandler;
    public GitlabConnector(UsersEntity user, CrudDAO databaseHandler) {
        this.user = user;
        this.databaseHandler = databaseHandler;
    }


    public List<ParsedCommit> requestData(TasksEntity task) throws GitLabApiException {
       return getCommits(new GitLabClient(user).getCommitList(),task.getJiraId());
    }

    public int calculateCommitSize(List<ParsedCommit> commitList, String taskId){
        int fullSize = 0;
        for (ParsedCommit commit: commitList) {
            if (commit.getMessage().matches("\\[( " + taskId + ")\\]")){
                fullSize+=commit.getAdditions();
            }
        }
        return fullSize;
    }

    public List<ParsedCommit> getCommits(List<ParsedCommit> commitList, String taskId){
        List<ParsedCommit> matchedCommits = new ArrayList<>();
        for (ParsedCommit commit: commitList) {
            if (commit.getMessage().matches("\\[( " + taskId + ")\\]")){
                  matchedCommits.add(commit);
            }
        }
        return matchedCommits;
    }

    public void commitDataUpdater(TasksEntity task, List<ParsedCommit> commitList){

    }


    public double getMultiplier(int size){
        if(size<=10){
            return 0.05;
        }
        if(size>10&&size<=50){
            return 0.1;
        }
        if(size>50&&size<100){
            return 0.15;
        }
        if(size>100&&size<=200){
            return 0.2;
        }
        else return 0.25;
    }
}
