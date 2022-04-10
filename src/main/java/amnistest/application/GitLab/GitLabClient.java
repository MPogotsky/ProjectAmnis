package amnistest.application.GitLab;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.CommitStats;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

import java.util.ArrayList;
import java.util.List;


public class GitLabClient {
    private String personalAccessToken;
    private String hostUrl;
    private String projectId;
    private GitLabApi restClient;
    private List<Commit> commits;

    /**
     * Default predefined constructor
     */
    public GitLabClient() {
        this.hostUrl = "https://gitlab.com";
        this.personalAccessToken = "glpat-vXrmFoCePMU_p79y2P_a";
        this.projectId = "34676173";
        this.restClient = getRestClient();
        this.commits = new ArrayList<Commit>();
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
        this.commits = new ArrayList<Commit>();
    }

    /**
     * Get rest client for GitLab API
     * @return rest client for GitLab
     */
    private GitLabApi getRestClient() {
        return new GitLabApi(hostUrl, personalAccessToken);
    }

    /**
     * Print current user
     */
    public void getUser() throws GitLabApiException {
        User currentUser = restClient.getUserApi().getCurrentUser();
        System.out.println("GitLab User: " + currentUser.getUsername());
    }


    /**
     * Get all commits from project defined by projectId
     */
    public void getCommitHistory() throws GitLabApiException {
        //TODO: Add segregation of commits by commit-type(merge, standard commit and so on)
        Project project = restClient.getProjectApi().getProject(projectId);
        commits = restClient.getCommitsApi().getCommits(project, null, null, null, null, null, true, null);
    }

    /**
     * Parse commit from list of commits to ParsedCommit obj
     * @return List of ParsedCommit
     */
    public List<ParsedCommit> getParsedCommitList() throws GitLabApiException {
        List<ParsedCommit> parsedCommits = new ArrayList<ParsedCommit>();
        for (Commit commit : commits) {
            ParsedCommit parsedCommit = new ParsedCommit();
            CommitStats stats = new CommitStats();

            parsedCommit.commiterEmail = commit.getCommitterEmail();
            parsedCommit.commiterName = commit.getCommitterName();
            parsedCommit.commitID = commit.getId();
            parsedCommit.message = commit.getMessage();
            parsedCommit.createdAt = commit.getCommittedDate();

            parsedCommit.additions = commit.getStats().getAdditions();
            parsedCommit.deletions = commit.getStats().getDeletions();
            parsedCommit.total = commit.getStats().getTotal();

            parsedCommits.add(parsedCommit);
        }
        return parsedCommits;
    }

}