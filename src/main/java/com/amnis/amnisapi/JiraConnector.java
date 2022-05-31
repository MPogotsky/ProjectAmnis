package com.amnis.amnisapi;

import com.amnis.model.*;

import java.util.ArrayList;
import java.util.List;

public class JiraConnector implements MainConnector {


    BasicDAO basicDAO;
    JiraClient jiraClient;
    public JiraConnector(){
        this.basicDAO = new CrudDAO();
    }



    @Override
    public void requestData(String taskId, String username){
        UsersEntity user = verifyUser(username);

        if(user!=null){




        }

    }

    @Override
    public void callToDB(String id){

        //Test logic!!!!


        int taskId = Integer.parseInt(id);



        TasksEntity currentTask = (TasksEntity) basicDAO.findEntityById(taskId, new TasksEntity());

        UsersEntity currentUser = (UsersEntity) basicDAO.findEntityById(currentTask.getUserId(), new UsersEntity());

//        CommitsEntity currentCommit = dbdao.findCommitById(currentTask.getId());

        System.out.println(currentTask);
        System.out.println(currentUser.toString());
//        System.out.println(currentTask.toString());


        //Test logic!!!!
    }

    public UsersEntity verifyUser(String username){

        List<UsersEntity> usersList = (List<UsersEntity>) basicDAO.findAllEntities(new UsersEntity());

        for(UsersEntity user: usersList){
            if(user.getLogin().equals(username)){
                return user;
            }
        }
        return null;
    }




}
