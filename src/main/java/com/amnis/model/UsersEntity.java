package com.amnis.model;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "server_database")
public class UsersEntity extends BasicEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "token")
    private String token;
    @Basic
    @Column(name = "gitlab_project_id")
    private Integer gitlabProjectId;
    @Basic
    @Column(name = "gitlab_token")
    private String gitlabToken;

    public Integer getGitlabProjectId() {
        return gitlabProjectId;
    }

    public void setGitlabProjectId(Integer gitlabProjectId) {
        this.gitlabProjectId = gitlabProjectId;
    }

    public String getGitlabToken() {
        return gitlabToken;
    }

    public void setGitlabToken(String gitlabToken) {
        this.gitlabToken = gitlabToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (gitlabProjectId != null ? !gitlabProjectId.equals(that.gitlabProjectId) : that.gitlabProjectId != null) return false;
        if (gitlabToken != null ? !gitlabToken.equals(that.gitlabToken) : that.gitlabToken != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (gitlabProjectId != null ? gitlabProjectId.hashCode() : 0);
        result = 31 * result + (gitlabToken != null ? gitlabToken.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", token='" + token + '\'' +
                ", gitlabProjectId=" + gitlabProjectId +
                ", gitlabToken='" + gitlabToken + '\'' +
                '}';
    }
}
