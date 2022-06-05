package com.amnis.amnisapi;

import com.amnis.model.UsersEntity;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.*;
import org.springframework.beans.factory.config.CustomEditorConfigurer;

import java.util.ArrayList;
import java.util.List;


public class GitLabClient {
    private String personalAccessToken;
    private String hostUrl;
    private String projectId;
    private GitLabApi restClient;
    private List<Commit> rawCommits;
    private UsersEntity user;

    /**
     * Parametric constructor
     * <p>
     * @param user     UserEntity object containing required information for Gitlab connecting
     */
    public GitLabClient(UsersEntity user) {
        this.user = user;
        this.hostUrl = "https://gitlab.com";
        this.personalAccessToken = user.getGitlabToken();
        this.projectId = user.getGitlabProjectId().toString();
        this.restClient = getRestClient();
        this.rawCommits = new ArrayList<>();
    }




    /**
     * Gets list of commits from project and
     * parses it.
     *
     *  ! Skips merge request commits !
     *
     * @return List of ParsedCommit
     */
    public List<ParsedCommit> getCommitList() throws GitLabApiException {
        Project project = getProjectByID(projectId);
        rawCommits = getRawCommits(project);
        List<ParsedCommit> parsedCommits = new ArrayList<>();
        for (Commit commit : rawCommits) {
            /*
             * If commit is merge request write it down to a separate list.
             * Do nothing with merge commit just keep it for now,
             * but it could be useful later, maybe.
             */
            if (!isMergeRequest(commit)) {
                if(commit.getCommitterEmail().equals(user.getLogin())){
                    ParsedCommit parsedCommit = getParsedCommit(commit);
                    parsedCommits.add(parsedCommit);
                }
            }

        }
        return parsedCommits;
    }

    /**
     * Get rest client for GitLab API based on
     * host URL and personal access token
     *
     * @return rest client for GitLab
     */
    private GitLabApi getRestClient() {
        return new GitLabApi(hostUrl, personalAccessToken);
    }

    /**
     * Get Gitlab API Project instance based on
     * project ID from GitLab webpage
     *
     * @return gitlab Project
     */
    private Project getProjectByID(String projectId) throws GitLabApiException {
        return restClient.getProjectApi().getProject(projectId);
    }

    /**
     * Gets all commits from the project
     * in a raw JSON format
     *
     * @return gitlab Project
     */
    private List<Commit> getRawCommits(Project project) throws GitLabApiException {
        return restClient.getCommitsApi().getCommits(project, null, null, null, null, null, true, null);
    }

    /**
     * Parse commit to ParsedCommit obj
     *
     * @return List of ParsedCommit
     */

    private ParsedCommit getParsedCommit(Commit commit){
        return new ParsedCommit(commit.getCommitterEmail(),commit.getCommitterName(),commit.getId(), commit.getMessage(),
                commit.getCommittedDate(),commit.getStats().getAdditions(),commit.getStats().getDeletions(),commit.getStats().getTotal());
    }

    /**
     * Returns: true if commit is merge request
     * and false if opposite
     *
     * @return Boolean
     */
    private boolean isMergeRequest(Commit examinedCommit) throws GitLabApiException {
        List<String> parenId = examinedCommit.getParentIds();
        return parenId.size() > 1;
    }

}
