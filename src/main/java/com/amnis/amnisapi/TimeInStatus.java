package com.amnis.amnisapi;

import java.math.BigInteger;

public class TimeInStatus {
    public String column;
    public Long timesInColumn;
    public Long stayedInColumn;
    @Override
    public String toString() {
        return "TimeInStatus{" +
                "column='" + column + '\'' +
                ", timesInColumn=" + timesInColumn +
                ", stayedInColumn=" + stayedInColumn +
                '}';
    }



    public TimeInStatus(String column, Long timesInColumn,Long stayedInColumn){
        this.column = column;
        this.timesInColumn = timesInColumn;
        this.stayedInColumn = stayedInColumn;
    }

}