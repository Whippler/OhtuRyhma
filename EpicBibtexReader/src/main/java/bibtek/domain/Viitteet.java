/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bibtek.domain;

import bibtek.domain.Reference;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Kalle
 */
public class Viitteet {
    private HashMap<String, Reference> viitteet; // id, viite
    
    
    public Viitteet(){
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
    public HashSet<Reference> haeViitteista(String s) {
        HashSet<Reference> founded = new HashSet<Reference>();
        for (Reference r : viitteet.values()) {
            if (r.toString().contains(s)) {
                founded.add(r);
            }
        }
        if (founded.isEmpty()) {
            return null;
        } else {
            String d = "";
            for (Reference r : founded) {
                d += r.toString() + "\n";
            }
            return founded;
        }
    }

    public HashMap<String, Reference> getViitteet() {
        return viitteet;
    }
    
    public boolean containsKey(String key){
        if (viitteet.containsKey(key)) return true;
        else return false;
    }

    @Override
    public String toString() {
        String paluu = "";
        for (Reference r : viitteet.values()){
            HashMap<String, String> data = r.getData();
            paluu += data.get("title") + " - " + data.get("author");
            paluu += "\n";
        }
        
        return paluu;
    }
    
}
