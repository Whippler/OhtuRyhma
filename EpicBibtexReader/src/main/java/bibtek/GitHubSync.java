/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bibtek;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author retsi
 */
public class GitHubSync {
    
    String[] add = {"git", "add", "-A"};
    String[] commit = {"git", "commit", "-m", ""};
    String[] push = {"git", "push"};
    String[] pull = {"git", "pull"};

    public GitHubSync() {
        run();
    }

    public void run() {
        
        Scanner lukija = new Scanner(System.in);
        String path = "";
        String commitmsg = "";
        
        System.out.println("Give path to git repository (eg: in format \"/home/username/myproject/myrepo/\"");
        path = lukija.nextLine();
        System.out.println("Give commit message:");
        commitmsg = lukija.nextLine();
        commit[3] = commitmsg;
        GitHubCommand add = new GitHubCommand(path, this.add);
        GitHubCommand commit = new GitHubCommand(path, this.commit);
        GitHubCommand push = new GitHubCommand(path, this.push);
        
        add.run();
        commit.run();
        push.run();
        
    }
}
