package com.amnis.model;

import javax.persistence.*;

@Entity
@Table(name = "commits", schema = "server_database")
public class CommitsEntity extends BasicEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int Id;
    @Basic
    @Column(name = "task_id")
    private Integer taskId;
    @Basic
    @Column(name = "task_status")
    private Integer taskStatus;
    @Basic
    @Column(name = "commited_value")
    private Integer commitedValue;
    @Basic
    @Column(name = "points")
    private Integer points;

    public int getId() {
        return Id;
    }

    public void setId(int commitId) {
        this.Id = commitId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getCommitedValue() {
        return commitedValue;
    }

    public void setCommitedValue(Integer commitedValue) {
        this.commitedValue = commitedValue;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommitsEntity that = (CommitsEntity) o;

        if (Id != that.Id) return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (taskStatus != null ? !taskStatus.equals(that.taskStatus) : that.taskStatus != null) return false;
        if (commitedValue != null ? !commitedValue.equals(that.commitedValue) : that.commitedValue != null)
            return false;
        if (points != null ? !points.equals(that.points) : that.points != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + (taskStatus != null ? taskStatus.hashCode() : 0);
        result = 31 * result + (commitedValue != null ? commitedValue.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommitsEntity{" +
                "commitId=" + Id +
                ", taskId=" + taskId +
                ", taskStatus=" + taskStatus +
                ", commitedValue=" + commitedValue +
                ", points=" + points +
                '}';
    }
}
