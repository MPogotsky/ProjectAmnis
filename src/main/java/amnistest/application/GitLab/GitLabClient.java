package amnistest.application.GitLab;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.*;

import java.util.ArrayList;
import java.util.List;


public class GitLabClient {
    private String personalAccessToken;
    private String hostUrl;
    private String projectId;
    private GitLabApi restClient;
    private List<Commit> rawCommits;
    private List<Commit> mergeCommits;

    /**
     * Default predefined constructor
     */
    public GitLabClient() {
        this.hostUrl = "https://gitlab.com";
        this.personalAccessToken = "glpat-vXrmFoCePMU_p79y2P_a";
        this.projectId = "34676173";
        this.restClient = getRestClient();
        this.rawCommits = new ArrayList<Commit>();
        this.mergeCommits = new ArrayList<Commit>();
    }

    /**
     * Parametric constructor
     * <p>
     *
     * @param hostUrl             gitlab server URL
     * @param personalAccessToken token to get access to the repo
     * @param projectId           project ID from GitLab repo
     *                            </p>
     */
    public GitLabClient(String hostUrl, String personalAccessToken, String projectId) {
        this.hostUrl = hostUrl;
        this.personalAccessToken = personalAccessToken;
        this.projectId = projectId;
        this.restClient = getRestClient();
        this.rawCommits = new ArrayList<Commit>();
        this.mergeCommits = new ArrayList<Commit>();
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
        List<ParsedCommit> parsedCommits = new ArrayList<ParsedCommit>();
        for (Commit commit : rawCommits) {
            /*
             * If commit is merge request write it down to a separate list.
             * Do nothing with merge commit just keep it for now,
             * but it could be useful later, maybe.
             */
            if (isMergeRequest(commit)) {
                mergeCommits.add(commit);
            } else {
                ParsedCommit parsedCommit = getParsedCommit(commit);
                parsedCommits.add(parsedCommit);
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
    private ParsedCommit getParsedCommit(Commit commit) throws GitLabApiException {
        ParsedCommit parsedCommit = new ParsedCommit();

        parsedCommit.commiterEmail = commit.getCommitterEmail();
        parsedCommit.commiterName = commit.getCommitterName();
        parsedCommit.commitID = commit.getId();
        parsedCommit.message = commit.getMessage();
        parsedCommit.createdAt = commit.getCommittedDate();

        parsedCommit.additions = commit.getStats().getAdditions();
        parsedCommit.deletions = commit.getStats().getDeletions();
        parsedCommit.total = commit.getStats().getTotal();

        return parsedCommit;
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