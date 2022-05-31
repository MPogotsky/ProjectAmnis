package com.amnis.model;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "tasks", schema = "server_database")
public class TasksEntity extends BasicEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "task_status")
    private Integer taskStatus;
    @Basic
    @Column(name = "estimated_time")
    private Time estimatedTime;
    @Basic
    @Column(name = "points")
    private Integer points;

    @Basic
    @Column(name = "jira_id")
    private String jiraId;

    public String getJiraId() { return jiraId; }

    public void setJiraId(String jiraId) { this.jiraId = jiraId;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Time getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Time estimatedTime) {
        this.estimatedTime = estimatedTime;
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

        TasksEntity that = (TasksEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (taskStatus != null ? !taskStatus.equals(that.taskStatus) : that.taskStatus != null) return false;
        if (estimatedTime != null ? !estimatedTime.equals(that.estimatedTime) : that.estimatedTime != null)
            return false;
        if (points != null ? !points.equals(that.points) : that.points != null) return false;
        if (jiraId != null ? !jiraId.equals(that.jiraId) : that.jiraId != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (taskStatus != null ? taskStatus.hashCode() : 0);
        result = 31 * result + (estimatedTime != null ? estimatedTime.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        result = 31 * result + (jiraId != null ? jiraId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TasksEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", taskStatus=" + taskStatus +
                ", estimatedTime=" + estimatedTime +
                ", points=" + points +
                ", jiraId=" + jiraId +
                '}';
    }
}
