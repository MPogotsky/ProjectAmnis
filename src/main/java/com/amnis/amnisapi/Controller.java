package com.amnis.amnisapi;

import com.amnis.model.CommitsEntity;
import com.amnis.model.CrudDAO;
import com.amnis.model.TasksEntity;
import com.amnis.model.UsersEntity;
import org.gitlab4j.api.GitLabApiException;

import java.util.List;

public class Controller {
    private final CrudDAO databaseHandler;
    private String taskId;
    private String username;
    private String companyURI;

    public Controller(String taskId, String username, String companyURI) {
        this.databaseHandler = new CrudDAO();
        this.taskId = taskId;
        this.username = username;
        this.companyURI = companyURI;
    }

    public void updateDatabase() throws GitLabApiException {
        UsersEntity user = findUser(username);
        if (user != null) {
            TasksEntity task = new JiraConnector(user, databaseHandler).requestData(taskId, companyURI);
            if (task != null) {
                databaseHandler.saveOrUpdateEntity(task);
                if (task.getTaskStatus() == 3) {
                    GitlabConnector gitlabConnector = new GitlabConnector(user, databaseHandler);
                    List<ParsedCommit> commitsList = gitlabConnector.requestData(task);
                    int updatedPointsValue = (int) (task.getPoints() * gitlabConnector.getMultiplier(gitlabConnector.calculateCommitSize(commitsList,task.getJiraId())));
                    List<TasksEntity> updatedTasks = (List<TasksEntity>) databaseHandler.findFieldValuesByCondition("jira_id", new TasksEntity(), task.getJiraId());
                    for(TasksEntity updated: updatedTasks){
                        if(task.getJiraId().equals(updated.getJiraId())){
                            task=updated;
                            break;
                        }
                    }
                    CommitsEntity commitsEntity = new CommitsEntity(task.getId(), gitlabConnector.calculateCommitSize(commitsList,task.getJiraId()),task.getPoints()-updatedPointsValue);
                    databaseHandler.saveEntity(commitsEntity);
                    task.setPoints(updatedPointsValue);
                    databaseHandler.saveOrUpdateEntity(task);
                }
            }
        }
    }


    public UsersEntity findUser(String username) {

        List<UsersEntity> usersList = (List<UsersEntity>) databaseHandler.findAllEntities(new UsersEntity());

        for (UsersEntity user : usersList) {
            if (user.getLogin().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
