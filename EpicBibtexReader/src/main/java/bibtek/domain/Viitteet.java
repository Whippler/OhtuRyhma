/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bibtek.domain;

import java.util.Arrays;
import java.util.HashMap;
/**
 *
 * @author Kalle
 */
public class Viitteet {

    private HashMap<String, Reference> viitteet; // id, viite

    public Viitteet() {
        viitteet = new HashMap<String, Reference>();
    }

    public boolean add(Reference viite) {
        if (viitteet.containsKey(viite.getId())) {
            return false;
        } else {
            viitteet.put(viite.getId(), viite);
            return true;
        }

    }

    public boolean delete(String id) {
        if (viitteet.containsKey(id)) {
            viitteet.remove(id);
            return true;
        }
        return false;
    }

    public String haeViitteista(String type, String term) {
        String ty = type.toLowerCase();
        String match = "";
        int count = 1;
        for (Reference r : viitteet.values()) {
            if (type.isEmpty()) {
                if (r.toString().contains(term)) {
                    match += count+". Author: "+r.getData().get("author") + 
                                    "\nTitle: " + r.getData().get("title") + "\n";
                    count++;
                }
            } else if(!Arrays.toString(r.fieldtypes).contains(ty)
                    && !Arrays.toString(r.entrytypes).contains(ty)
                    && !ty.equals("id")){
                return "No field/entrytypes found";
            }
            else if ( (r.getData().containsKey(ty) && r.getData().get(ty).contains(term)) 
                    || ( ty.equals("id") && r.getId().contains(term))
                    || (Arrays.toString(r.entrytypes).contains(ty) && r.getEntrytype().equals(ty) && r.toString().contains(term)))
            {
                match += count+". Author: "+r.getData().get("author") + 
                                    "\nTitle: " + r.getData().get("title") + "\n";
                count++;
            }
        }
        if (match.isEmpty()) {
            return "No matches found!";
        } else {
            return match;
        }
    }

    public HashMap<String, Reference> getViitteet() {
        return viitteet;
    }

    public boolean containsKey(String key) {
        if (viitteet.containsKey(key)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String paluu = "";
        for (Reference r : viitteet.values()) {
            HashMap<String, String> data = r.getData();
            paluu += r.getId() + ": " + data.get("title") + " - " + data.get("author");
            paluu += "\n";
        }

        return paluu;
    }

    public String viitteetInBibtex() {
        String tmp = "";
        for (Reference r : viitteet.values()) {
            tmp += r.refInBibtex();
            tmp += "\n";
        }
        return tmp;
    }
}
