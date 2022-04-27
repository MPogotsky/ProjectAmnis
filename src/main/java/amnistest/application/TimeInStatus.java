package amnistest.application;

public class TimeInStatus {
    public String column;
    public int timesInColumn;

    @Override
    public String toString() {
        return "TimeInStatus{" +
                "column='" + column + '\'' +
                ", timesInColumn=" + timesInColumn +
                ", stayedInColumn=" + stayedInColumn +
                '}';
    }

    public int stayedInColumn;

    public TimeInStatus(String column, int timesInColumn,int stayedInColumn){
        this.column = column;
        this.timesInColumn = timesInColumn;
        this.stayedInColumn = stayedInColumn;
    }

    public TimeInStatus(){
        this.column = "";
        this.timesInColumn = 0;
        this.stayedInColumn = 0;
    };
}
