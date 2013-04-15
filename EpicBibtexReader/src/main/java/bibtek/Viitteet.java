/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bibtek;

import bibtek.domain.Reference;
import java.util.HashMap;

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

    public HashMap<String, Reference> getViitteet() {
        return viitteet;
    }
    
}
