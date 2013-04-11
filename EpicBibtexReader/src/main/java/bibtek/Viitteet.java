/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bibtek;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Kalle
 */
public class Viitteet {
    private HashMap<String, HashMap<String, String>> viitteet; // id, viite
    
    
    public void Viitteet(){
        viitteet = new HashMap<String, HashMap<String, String>>();
    }
    
    public boolean add(String id, HashMap<String, String> viite){
        if(viitteet.containsKey(id)){
            return false;
        } else {
            viitteet.put(id, viite);
            return true;
        }
        
    }
    
}
