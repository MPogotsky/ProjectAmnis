package com.amnis.amnisapi;

import com.amnis.model.CrudDAO;
import com.amnis.model.TasksEntity;
import com.amnis.model.UsersEntity;

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

    public void updateDatabase() {
        UsersEntity user = findUser(username);
        if (user != null) {
            TasksEntity task = new JiraConnector(user, databaseHandler).requestData(taskId, companyURI);
            if (task != null) {
                databaseHandler.saveOrUpdateEntity(task);
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
