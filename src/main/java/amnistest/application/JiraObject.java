package amnistest.application;

import java.lang.invoke.MutableCallSite;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class JiraObject {
    public Integer workRatio;
    public Integer originalEstimate;
    public Integer remainingEstimate;
    public List<TimeInStatus> timeInStatuses;

    public JiraObject(JiraClient jiraClient, String IssueKey){
        this.workRatio = Integer.parseInt(jiraClient.getFieldValue(IssueKey,FieldID.WORK_RATIO.id));
        this.originalEstimate = Integer.parseInt(jiraClient.getFieldValue(IssueKey,FieldID.TIME_ORIGINAL_ESTIMATE.id));
        this.remainingEstimate = Integer.parseInt(jiraClient.getFieldValue(IssueKey,FieldID.TIME_ESTIMATE.id));
        this.timeInStatuses = jiraClient.getTimeinStatus(IssueKey);
    }

    public int calculateTaskValue(){
        int value = 0;
        List<Double> multipliers = createMultiplierList();
        if (!this.timeInStatuses.isEmpty()) {
            for (int ts = 1; ts < timeInStatuses.size() - 1; ts++) {
                value += calculateColumnValue(timeInStatuses.get(ts).stayedInColumn,multipliers.get(ts - 1));
            }
            int leftTimeValue = calculateLeftTimeValue(value);
            value += leftTimeValue;
        }
        return value;
    }

    public int calculateColumnValue(int timeInColumn, Double multiplier){
        return (timeInColumn*multiplier.intValue());
    }

    public int calculateLeftTimeValue(int sumOfColumns){
        return originalEstimate - sumOfColumns;
    }

    public List<Double> createMultiplierList(){
        List<Double> multiplicators = new ArrayList<>();
        double k = 0.0;
        for (int ts = 1; ts < timeInStatuses.size() - 1; ts++){
            k += 0.1;
            multiplicators.add(k);
        }
        return multiplicators;
    }

    public void print(){
        System.out.println(this.workRatio);
        System.out.println(this.originalEstimate);
        System.out.println(this.remainingEstimate);
        System.out.println(this.timeInStatuses.toString());
    }
}
