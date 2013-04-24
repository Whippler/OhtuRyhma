
package bibtek.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author retsi
 */
public class GitHubCommand {
    
    String path = "";
    String[] komennot;
    
    public GitHubCommand(String path, String[] komennot){
        
        this.path = path;
        this.komennot = komennot;
    }
    
    
    
    public void run() {

        String s = "";

        try {
            ProcessBuilder pb = new ProcessBuilder(komennot);
            pb.directory(new File(path));
            Process p = pb.start();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
            
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            
        } catch (IOException e) {
            System.out.println("IO Exception:");
            e.printStackTrace();
        }
    }
    
}
