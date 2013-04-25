package bibtek.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Reference {
    
    public final String[] fieldtypes = {"address", "annote", "author", "booktitle", "chapter", "crossref", "edition",
            "editor", "howpublished", "institution", "journal", "key", "month", "note", "number",
            "organization", "pages", "publisher", "school", "series", "title", "type", "volume", "year"};
    public final String[] entrytypes={"article", "book", "booklet", "conference", "inbook",
            "incollection", "inproceedings", "manual", "mastersthesis",
            "misc", "phdthesis", "proceedings", "techreport", "unpublished",
            "collection"};
       
    private HashMap<String, String> data;
    private String id, entryType;

    public Reference() {
        this.id = "";
        this.entryType = "";
        data = new HashMap<String, String>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }
    
    public HashMap<String, String> getData() {
        return data;
    }

    //asettaa viitteen tyypin, esim. article, book jne.
    public boolean setEntryType(String entryType) {
        boolean match = false;
        for (String s : entrytypes) {
            if (s.equalsIgnoreCase(entryType)) {
                match = true;
            }
        }
        this.entryType = entryType;
        return match;
    }

    //palauttaa kentistä author ja year generoidun idn 
    // SIIRRETTY BIBTEX LUOKKAAN
//    public String generoiId(String author, String year) {
//        String retID = "";
//        for (String s : author.split(" and ")) {
//            retID += s.charAt(0);
//        }
//        retID += year.substring(year.length() - 2);
//        return retID;
//    }
    //tallentaa kentän nimen ja sisällön hashmappiin
    public boolean setField(String field, String content) {
        
        boolean match = false;
        for (String s : fieldtypes) {
            if (field.equalsIgnoreCase(s)) {  //tarkistetaan onko kenttä jokin hyväksytyistä
                match = true;
            }
        }
        if (match && content.length() > 0) {  //jos on, ja jos kentän sisältö ei tyhjä, lisätään hashmappiin
            data.put(field, content);
            return true;
        }
        return false;
    }

    //ääkköset bibtex-muotoon
    public String korjaaKirjaimet(String s) {
        if (s.contains("{a}") || s.contains("{o}")
                || s.contains("{A}") || s.contains("{O}")
                || s.contains("{\\aa}") || s.contains("{\\AA}")) {
            s = s.replace("\\\"{a}", "ä");
            s = s.replace("\\\"{A}", "Ä");
            s = s.replace("\\\"{o}", "ö");
            s = s.replace("\\\"{O}", "Ö");
            s = s.replace("{\\aa}", "å");
            s = s.replace("{\\AA}", "Å");
        }
        else {
            s = s.replace("ä", "\\\"{a}");
            s = s.replace("Ä", "\\\"{A}");
            s = s.replace("ö", "\\\"{o}");
            s = s.replace("Ö", "\\\"{O}");
            s = s.replace("å", "{\\aa}");
            s = s.replace("Å", "{\\AA}");
        }
        return s;
    }

    public String refInBibtex() {
        //jos jokin vaadituista kentistä tyhjä, palautetaan null
        if (this.data.get("year") == null || this.data.get("author") == null || this.data.get("title") == null) {
            return null;
        }
        String tmp = "";
        Iterator i = this.data.entrySet().iterator();

//        this.id = generoiId(this.data.get("author"), this.data.get("year")); //kirjoitetaan ensin
        tmp += "@" + this.entryType + "{" + this.id + ",\n";                 //otsakkeen tiedot

        //kahteen ekaan kenttään author ja title, loput voivat olla missä järj. tahansa
        tmp += "author = {" + data.get("author") + "},\n";
        tmp += "title = {" + data.get("title") + "},\n";

        //iteroidaan HashMapin läpi ja tallennetaan sen tiedot Stringiin
        while (i.hasNext()) {
            Map.Entry entry = (Map.Entry) i.next();
            String field = (String) entry.getKey();
            String content = (String) entry.getValue();
            if (!field.equalsIgnoreCase("author") && !field.equalsIgnoreCase("title")) { //ettei lisätä 2 kertaan
                tmp += field + " = {" + content + "},\n";
            }
        }
        tmp += "}\n\n";
        return korjaaKirjaimet(tmp);
    }

    public String refInPlainText() {
        String tmp = this.refInBibtex();
        tmp=korjaaKirjaimet(tmp);

        tmp = tmp.replaceAll("\\@.*\\,", "");
        tmp = tmp.replaceAll(" \\{", "");
        tmp = tmp.replaceAll("},", "");
        tmp = tmp.replaceAll("}", "");
        tmp = tmp.replaceAll(" =", ": ");

        tmp = tmp.substring(0, tmp.length() - 2);
        return tmp;
    }
    
    public boolean checkIfValid(){
        if(data.containsKey("author") && data.containsKey("year") && data.containsKey("title"))
            return true;
        return false;
    }
    
    @Override
    public String toString() {
        return refInPlainText();
    }
}
