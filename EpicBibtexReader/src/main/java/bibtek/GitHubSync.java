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

    public GitHubSync() {
        run();
    }

    public void run() {
        
        Scanner lukija = new Scanner(System.in);
        String path = "";
        String commitmsg = "";
        
        System.out.println("Give path to git repository (eg: in format \"/home/username/myproject/myrepo/\"");
        path = lukija.nextLine();
        System.out.println(path);
        System.out.println("Give commit message:");
        commitmsg = lukija.nextLine();
        add(path);
        commit(path, commitmsg);
        push(path);
        
    }
    
    public static void add(String path) {

        String s = "";

        try {
            ProcessBuilder pb = new ProcessBuilder("git", "add", "-A");
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
    
    public static void commit(String path, String commit) {

        String s = "";

        try {
            ProcessBuilder pb = new ProcessBuilder("git", "commit", "-m", "\""+commit+"\"");
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
    
    public static void pull(String path, String commit) {

        String s = "";

        try {
            ProcessBuilder pb = new ProcessBuilder("git", "pull");
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
    
    public static void push(String path) {

        String s = "";

        try {
            ProcessBuilder pb = new ProcessBuilder("git", "push");
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
