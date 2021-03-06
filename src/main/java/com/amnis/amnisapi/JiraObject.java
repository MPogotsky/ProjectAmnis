package com.amnis.amnisapi;

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
        this.timeInStatuses = jiraClient.getTimesInStatuses(IssueKey);
    }

    /**
     *
     * Calculates points for the task which is done
     *
     */
    //TODO LUKA
    public Long calculateTaskValue(){
        Long value = 0L;
        List<Double> multipliers = createMultiplierList();
        if (!this.timeInStatuses.isEmpty()) {
            for (int ts = 1; ts < timeInStatuses.size() - 1; ts++) {
                value += calculateColumnValue(timeInStatuses.get(ts).stayedInColumn,multipliers.get(ts - 1));
            }
            Long leftTimeValue = calculateLeftTimeValue(value);
            value += leftTimeValue;
        }
        return value;
    }

    //TODO LUKA
    public Time calculateOverallTime(){
        return new Time(1,0,0);
    }

    /**
     *
     * Calculates points for one concrete column
     *
     * <p>
     * @param timeInColumn time task spent in column
     * @param multiplier column value multiplier
     * <p/>
     */
    public Long calculateColumnValue(Long timeInColumn, Double multiplier){
        return (timeInColumn*multiplier.intValue());
    }

    /**
     *
     * Calculates points for one concrete column
     *
     * <p>
     * @param sumOfColumns sum of all column values on the board
     * <p/>
     */
    public Long calculateLeftTimeValue(Long sumOfColumns){
        return originalEstimate - sumOfColumns;
    }

    /**
     *
     * Creates a list of column multipliers
     *
     */
    public List<Double> createMultiplierList(){
        List<Double> multipliers = new ArrayList<>();
        double k = 0.0;
        for (int ts = 1; ts < timeInStatuses.size() - 1; ts++){
            k += 0.1;
            multipliers.add(k);
        }
        return multipliers;
    }

}