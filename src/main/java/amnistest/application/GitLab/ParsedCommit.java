package amnistest.application.GitLab;

import java.util.Date;

public class ParsedCommit {
    public String commiterEmail;
    public String commiterName;
    public String commitID;
    public String message;
    public java.util.Date createdAt;

    public Integer additions;
    public Integer deletions;
    public Integer total;

    public ParsedCommit(){
        this.commiterEmail = "";
        this.commiterName = "";
        this.commitID = "";
        this.message = "";
        this.createdAt = new Date();
        this.additions = 0;
        this.deletions = 0;
        this.total = 0;
    }

    public void PrintData(){
        System.out.println("commiterEmail:" + commiterEmail);
        System.out.println("commiterName:" + commiterName);
        System.out.println("commitID:" + commitID);
        System.out.println("message:" + message);
        System.out.println("createdAt:" + createdAt);

        System.out.println("Stats: additions:" + additions);
        System.out.println("Stats: deletions:" + deletions);
        System.out.println("Stats: total::" + total);
        System.out.println();
    }
}
