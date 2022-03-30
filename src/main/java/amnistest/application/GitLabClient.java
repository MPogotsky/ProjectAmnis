package amnistest.application;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

import java.util.List;

public class GitLabClient {
    public String personalAccessToken;
    public String hostUrl;
    public String projectId;
    public GitLabApi restClient;

    /**
     * Default predefined constructor
     */
    public GitLabClient() {
        this.hostUrl = "https://gitlab.com";
        this.personalAccessToken = "glpat-vXrmFoCePMU_p79y2P_a";
        this.projectId = "34676173";
        this.restClient = getRestClient();
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
    }

    private GitLabApi getRestClient() {
        return new GitLabApi(this.hostUrl, this.personalAccessToken);
    }

    /**
     * Prints current user
     */
    public void getUser() throws GitLabApiException {
        User currentUser = restClient.getUserApi().getCurrentUser();
        System.out.println("GitLab User: " + currentUser.getUsername());
    }

    /**
     * Prints all commits from project
     */
    public void getCommitHistory() throws GitLabApiException {
        Project project = restClient.getProjectApi().getProject("34676173");
        List<Commit> commits = restClient.getCommitsApi().getCommits(project);
        System.out.println("Project commit history: \n" + commits);
    }
}
