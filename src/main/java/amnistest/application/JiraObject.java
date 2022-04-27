package amnistest.application;

import java.util.ArrayList;
import java.util.List;

public class JiraObject {
    public String workRatio;
    public String startDate;
    public String originalEstimate;
    public String remainingEstimate;
    public String progress;
    public List<TimeInStatus> timeInStatuses;

    public JiraObject(JiraClient jiraClient){
        this.workRatio = jiraClient.getFieldValue("AP-10","workratio");
        this.startDate = jiraClient.getFieldValue("AP-10","customfield_10015");
        this.originalEstimate = jiraClient.getFieldValue("AP-10","timeoriginalestimate");
        this.remainingEstimate = jiraClient.getFieldValue("AP-10","timeestimate");
        this.progress = jiraClient.getFieldValue("AP-10","progress");
        this.timeInStatuses = jiraClient.getTimeinStatus("AP-10");
    }

    public int calculateTaskValue(){
        Integer.parseInt(workRatio);
        return 0;
    }

    public void print(){
        System.out.println(this.workRatio);
        System.out.println(this.startDate);
        System.out.println(this.originalEstimate);
        System.out.println(this.remainingEstimate);
        System.out.println(this.progress);
        System.out.println(this.timeInStatuses.toString());
    }
}
