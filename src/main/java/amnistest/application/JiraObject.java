package amnistest.application;

import java.util.ArrayList;
import java.util.List;

public class JiraObject {
    public String workRatio; // Time spent/original estimate  * 100
    public String startDate; // START DATE
    public String originalEstimate;
    public String remainingEstimate;
    public String progress;
    public List<TimeInStatus> timeInStatuses;

}
