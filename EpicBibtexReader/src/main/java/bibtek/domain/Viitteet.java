/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bibtek.domain;

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
    
    public boolean add(Reference viite){
        if(viitteet.containsKey(viite.getId())){
            return false;
        } else {
            viitteet.put(viite.getId(), viite);
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
            paluu += r.getId()+": "+data.get("title") + " - " + data.get("author");
            paluu += "\n";
        }
        
        return paluu;
    }
    
    public String viitteetInBibtex() {
        String tmp="";
         for (Reference r : viitteet.values()) {
             tmp+=r.refInBibtex();
             tmp+="\n";
         }
         return tmp;
    }
    
}
