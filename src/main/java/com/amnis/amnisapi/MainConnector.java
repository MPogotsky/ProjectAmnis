package com.amnis.amnisapi;

public interface MainConnector {

    public void requestData(String taskId, String username);

    public void callToDB(String taskId);

}
