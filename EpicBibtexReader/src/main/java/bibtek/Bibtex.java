package bibtek;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


//Pääs unohtumaan yhdessä sovitut toteutus keinot, joten improvisoin.
//Saa muokata / tehdä mitä vain muutoksia mitä tulee mieleen
//Scanneria ei ole käytetty missään, joten käyttäjä ei saa syötettyä
//joku voisi tehdä sen, pitää sitten huomioida järjestys tossa tiedot[] <<--- korvasin tuon hashmapilla
//taulukossa

//refaktoroin nuo luokat java-tyylisemmäks alkamaan isolla kirjaimella :D
public class Bibtex {

    String type, id;
    private File refs;
    private HashMap<String, String> data;   //laitetaan tiedot HashMappiin, jossa key on kenttä, esim author
                                            //ja arvo kentän arvo 
    
    //parametriton konstruktori luo refs.bib -tiedoston, jos se ei ole 
    //ennestään olemassa
    public Bibtex() {
        this.refs = new File("refs.bib");  //ehkä käyttäjäkin voisi antaa tiedoston nimen jossain vaiheessa
        try {
            if (!this.refs.exists()) {
                this.refs.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        data = new HashMap<String, String>();
    }

    //laitoin String-taulukot pelkiksi Stringeiksi, mun puolesta
    //käyttäjä saa ite lisätä kaikki authorit kerralla, muissa taulukkoa
    //ei kai tarvi muutenkaan
    public Bibtex(String[] tiedot) {
        this();
        this.type = tiedot[0];
        data.put("author", tiedot[1]);
        data.put("title", tiedot[2]);
        data.put("journal", tiedot[3]);
        data.put("volume", tiedot[4]);
        data.put("number", tiedot[5]);
        data.put("year", tiedot[6]);
        data.put("pages", tiedot[7]);
        data.put("publisher", tiedot[8]);
        data.put("address", tiedot[9]);
        //...
    }

    //tallentaa tiedot yhteen refs.bib -tiedostoon
    public boolean tallennaTiedot() {
        try {
            FileWriter fw = new FileWriter(this.refs.getName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.toString());
            bw.close();
            fw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    //regexeillä poistetaan turhat hörhelöt ja muuttaa ääkköset selvätekstiksi
    public String tulostaSelkokielellä() {
        String tmp = this.toString();
        tmp = tmp.replace("\\\"{a}", "ä");
        tmp = tmp.replace("\\\"{A}", "Ä");
        tmp = tmp.replace("\\\"{o}", "ö");
        tmp = tmp.replace("\\\"{O}", "Ö");
        tmp = tmp.replace("\\{aa}", "å");
        tmp = tmp.replace("\\{AA}", "Å");

        tmp = tmp.replaceAll("\\@.*\\,", "");
        tmp = tmp.replaceAll(" \\{", "");
        tmp = tmp.replaceAll("},", "");
        tmp = tmp.replaceAll("}", "");
        tmp = tmp.replaceAll(" =", ": ");

        tmp = tmp.substring(0, tmp.length() - 2);
        return tmp;
    }

    //luetaan käyttäjältä tietoja terminaalista
    public void lueKayttajanSyotteet() {
        this.data.clear();  //alustetaan hashmap uudelleen joka kutsukerralla
        System.out.println("All other fields except author, title and year can be left empty");
        this.data.put("author", "");    //näissä kentissä pitää olla tekstiä
        this.data.put("title", "");
        this.data.put("year", "");
        this.type = "article";  //vielä ei tarvita muita tyyppejä
        Scanner sc = new Scanner(System.in);

        while (this.data.get("author").length() == 0) {
            System.out.print("Author(s): ");
            this.data.put("author", korjaaKirjaimet(sc.nextLine())); //jos indeksointiin tarvitaan bibtex-muodossa
            //jotkut virheilmoitukset näihin myöhemmin               //olevat ääkköset?
            //jos käyttäjä jättääkin tyhjäksi
        }
        while (this.data.get("title").length() == 0) {
            System.out.print("Title: ");
            this.data.put("title", sc.nextLine());
        }
        while (this.data.get("year").length() == 0) {
            System.out.print("Year: ");
            this.data.put("year", sc.nextLine());
        }
        System.out.print("Journal: ");
        this.data.put("journal", sc.nextLine());
        System.out.print("Volume: ");
        this.data.put("volume", sc.nextLine());
        System.out.print("Number: ");
        this.data.put("number", sc.nextLine());
        System.out.print("Pages: ");
        this.data.put("pages", sc.nextLine());
        System.out.print("Publisher: ");
        this.data.put("publisher", sc.nextLine());
        System.out.print("Address: ");
        this.data.put("address", sc.nextLine());

        //kaikkia mahdollisia kenttiä ei ole laitettu tähän, niitä voi lisätä 
        //helposti myöhemmin
    }

    //palauttaa W04 muotoisen idn 
    private String generoiId(String author, String year) {
        String retID = "";
        for (String s : author.split(" and ")) {
            retID += s.charAt(0);
        }
        retID += year.substring(year.length() - 2);
        return retID;
    }

    //Muutaa mahdolliset ät tai öt bibtekst muotoon ja päinvastoin :D
    private String korjaaKirjaimet(String s) {
        s = s.replace("ä", "\\\"{a}");
        s = s.replace("Ä", "\\\"{A}");
        s = s.replace("ö", "\\\"{o}");
        s = s.replace("Ö", "\\\"{O}");
        s = s.replace("å", "\\{aa}");
        s = s.replace("Å", "\\{AA}");
        return s;
    }

    //palauttaa bibtex muodossa tiedot, ääkkösiä ei ole hoidettu
    @Override
    public String toString() {
        String tmp = "";
        Iterator i = this.data.entrySet().iterator();

        id = generoiId(this.data.get("author"), this.data.get("year")); //kirjoitetaan ensin
        tmp += "@" + type + "{" + id + ",\n";              //otsakkeen tiedot

        //iteroidaan HashMapin läpi ja tallennetaan sen tiedot Stringiin
        //tyhjät kentät jätetään huomiotta
        while (i.hasNext()) {
            Map.Entry entry = (Map.Entry) i.next();
            if (entry.getValue().toString().length() > 0) {//tyhjiä ei kirjoiteta
                tmp += entry.getKey() + " = {" + entry.getValue() + "},\n";
            }
        }
        tmp += "}\n\n";
        return korjaaKirjaimet(tmp);
    }
}
