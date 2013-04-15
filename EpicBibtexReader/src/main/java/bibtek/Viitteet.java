/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bibtek;

import bibtek.domain.Reference;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Kalle
 */
public class Viitteet {
    private HashMap<String, Reference> viitteet; // id, viite
    
    public void Viitteet(){
        viitteet = new HashMap<String, Reference>();
    }
    
    public boolean add(String id, Reference viite){
        if(viitteet.containsKey(id)){
            return false;
        } else {
            viitteet.put(id, viite);
            return true;
        }
        
    }

    
    public String haeViitteista(String s){
        HashSet<Reference> founded = new HashSet<Reference>();
        for (Reference r: viitteet.values()){
            if (r.toString().contains(s)) founded.add(r);
        }
        if (founded.isEmpty())return "no matches";
        else {
            String d="";
            for (Reference r : founded){
                d+=r.toString()+"\n";
            }
            return d;
        }
    }

    public HashMap<String, Reference> getViitteet() {
        return viitteet;
    }
    
    
  
}
