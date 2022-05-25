package com.amnis.amnisapi;

import com.amnis.model.CommitsEntity;
import com.amnis.model.DatabaseDAO;
import com.amnis.model.TasksEntity;
import com.amnis.model.UsersEntity;
import org.hibernate.boot.model.relational.Database;

public class JiraConnector implements MainConnector {

    public JiraConnector(){
    }

    @Override
    public void requestData(String id){
        //create request for jira

    }

    @Override
    public void callToDB(String id){

        //Test logic!!!!

        DatabaseDAO dbdao  = new DatabaseDAO();

        int taskId = Integer.parseInt(id);

        TasksEntity currentTask = dbdao.findTaskById(taskId);

        UsersEntity currentUser = dbdao.findUserById(currentTask.getUserId());

//        CommitsEntity currentCommit = dbdao.findCommitById(currentTask.getId());

        System.out.println(currentTask.toString());
        System.out.println(currentUser.toString());
//        System.out.println(currentTask.toString());


        //Test logic!!!!
    }

}
