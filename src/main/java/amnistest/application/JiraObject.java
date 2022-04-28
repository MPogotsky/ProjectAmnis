package amnistest.application;

import java.lang.invoke.MutableCallSite;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class JiraObject {
    public String workRatio;
    public String startDate;
    public int originalEstimate;
    public String remainingEstimate;
    public String progress;
    public List<TimeInStatus> timeInStatuses;

    public JiraObject(JiraClient jiraClient){
        String AP = "AP-16";
        this.workRatio = jiraClient.getFieldValue(AP,"workratio");
        this.startDate = jiraClient.getFieldValue(AP,"customfield_10015");
        this.originalEstimate = Integer.parseInt(jiraClient.getFieldValue(AP,"timeoriginalestimate"));
        this.remainingEstimate = jiraClient.getFieldValue(AP,"timeestimate");
        this.progress = jiraClient.getFieldValue(AP,"progress");
        this.timeInStatuses = jiraClient.getTimeinStatus(AP);
    }

    public int calculateTaskValue(){
        int value = 0;
        List<Float> multipliers = createMultiplierList();
        if (!this.timeInStatuses.isEmpty()) {
            int realRatio = originalEstimate;
            for (int ts = 1; ts < timeInStatuses.size() - 1; ts++) {
                value += calculateColumnValue(timeInStatuses.get(ts).stayedInColumn,multipliers.get(ts - 1));
            }
            int leftTimeValue = calculateLeftTimeValue(value);
            value += leftTimeValue;
        }
        return value;
    }

    public int calculateColumnValue(int timeInColumn, float multiplier){
        return (int) (timeInColumn*multiplier);
    }

    public int calculateLeftTimeValue(int sumOfColumns){
        return originalEstimate - sumOfColumns;
    }

    public List<Float> createMultiplierList(){
        List<Float> multiplicators = new ArrayList<Float>();
        float k = 0.0f;
        for (int ts = 1; ts < timeInStatuses.size() - 1; ts++){
            k += 0.1f;
            multiplicators.add(k);
        }
        return multiplicators;
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
