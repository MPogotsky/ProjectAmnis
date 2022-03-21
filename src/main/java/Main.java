import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;

import java.util.List;

public class Main {
    public static void main(String[] args) throws GitLabApiException {
        GitLabApi gitLabApi = new GitLabApi("https://gitlab.com", "glpat-vXrmFoCePMU_p79y2P_a");

        User currentUser = gitLabApi.getUserApi().getCurrentUser();
        System.out.println(currentUser.getUsername());

        // Get the list of projects your account has access to
        Project project = gitLabApi.getProjectApi().getProject("34676173");
        List<Commit> commits = gitLabApi.getCommitsApi().getCommits(project);
        System.out.println(commits);
    }
}
