package com.amnis.amnisapi;

public enum TaskStatuses {

    COMPLETE("COMPLETE",3),INPROGRESS("IN_PROGRESS",2), TODO("TODO",1),UNDEFINED("UNDEFINED",0);

    final String status;
    final int statusValue;

    TaskStatuses(String status, int statusValue){
        this.status = status;
        this.statusValue = statusValue;
    }

    public final String getStatus(){
        return status;
    }

    public final int getStatusValue() {
        return statusValue;
    }
}
