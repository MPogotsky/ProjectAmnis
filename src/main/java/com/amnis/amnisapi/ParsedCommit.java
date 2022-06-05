package com.amnis.amnisapi;
import java.util.Date;

public class ParsedCommit {

    private String commiterEmail;
    private String commiterName;
    private String commitID;
    private String message;
    private java.util.Date createdAt;
    private Integer additions;
    private Integer deletions;
    private Integer total;

    public String getCommiterEmail() {
        return commiterEmail;
    }

    public void setCommiterEmail(String commiterEmail) {
        this.commiterEmail = commiterEmail;
    }

    public String getCommiterName() {
        return commiterName;
    }

    public void setCommiterName(String commiterName) {
        this.commiterName = commiterName;
    }

    public String getCommitID() {
        return commitID;
    }

    public void setCommitID(String commitID) {
        this.commitID = commitID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAdditions() {
        return additions;
    }

    public void setAdditions(Integer additions) {
        this.additions = additions;
    }

    public Integer getDeletions() {
        return deletions;
    }

    public void setDeletions(Integer deletions) {
        this.deletions = deletions;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ParsedCommit(String commiterEmail, String commiterName, String commitID, String message, Date createdAt, Integer additions, Integer deletions, Integer total) {
        this.commiterEmail = commiterEmail;
        this.commiterName = commiterName;
        this.commitID = commitID;
        this.message = message;
        this.createdAt = createdAt;
        this.additions = additions;
        this.deletions = deletions;
        this.total = total;
    }

    @Override
    public String toString() {
        return "ParsedCommit{" +
                "commiterEmail='" + commiterEmail + '\'' +
                ", commiterName='" + commiterName + '\'' +
                ", commitID='" + commitID + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", additions=" + additions +
                ", deletions=" + deletions +
                ", total=" + total +
                '}';
    }
}