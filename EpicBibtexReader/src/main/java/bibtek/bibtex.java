package bibtek;

import java.io.*;

//Pääs unohtumaan yhdessä sovitut toteutus keinot, joten improvisoin.
//Saa muokata / tehdä mitä vain muutoksia mitä tulee mieleen
//Scanneria ei ole käytetty missään, joten käyttäjä ei saa syötettyä
//joku voisi tehdä sen, pitää sitten huomioida järjestys tossa tiedot[]
//taulukossa


public class bibtex {

    String type, id;
    String[] author, title, journal, volume, number, year, pages, publisher, address;
    
    //Konstuktori saa 2 ulotteisen string taulukon, joka sisältää viitetiedot
    //Pitää miettiä miten toteutetaan muut viitetyypit, joissa on eri tiedot.
    public bibtex(String[][] tiedot) {
        type = tiedot[0][0];
        author = tiedot[1];
        title = tiedot[2];
        journal = tiedot[3];
        volume = tiedot[4];
        number = tiedot[5];
        year = tiedot[6];
        pages = tiedot[7];
        publisher = tiedot[8];
        address = tiedot[9];
        //...
        id = generoiId(author, year);
        //... Tähä vois tietenkin keksii jonku paremman toteutuksen...
        

    }


    //Parametrina file, tiedoston nimi
    //Pitää miettiä, miten useamman kanssa toimii
    //Palauttaa true, jos tallenus onnistuu
    //pitää vielä siistiä ääkköset
    public boolean tallennaTiedot(File f) {
        try {
            FileWriter fr = new FileWriter(f);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(toString());
            br.close();
            fr.close();
            return true;
        } catch (Exception e) {
            System.out.println("Tiedoston tallennuksessa tapahtui virhe");
            return false;
        }
    }
    //Tätä pitäisi hioa, sangen ruma toteutus
    public String tulostaSelkokielellä() { //vikat pilkut pitäs saada pois
       String tmp = this.toString();
       tmp = tmp.replaceAll(" \\{", "");
       tmp = tmp.replaceAll("}", "");
       tmp = tmp.replaceAll(" =", ": ");
       tmp = tmp.substring(0, tmp.length()-2);
       return tmp;
    }

    //palauttaa W04 muotoisen idn 
    private String generoiId(String[] author, String[] year) {
        return "eiTehty";
    }
    
    //Muutaa mahdolliset ät tai öt bibtekst muotoon ja päinvastoin :D
    private String korjaaKirjaimet(String s) {
        return "EIOLETEHTYYYYY";
    }
    
    //palauttaa {String, taulukon}, tänne olisi hyvä kätkeä ÄÄ ÖÖ hoito tai tehdä
    //uusi metodi.
    private String StringArraytoString(String[] s){
        try {
        String tmp = "{";
        for(String part : s){
            tmp=tmp.concat(part+", ");
        }
       
        return tmp.substring(0, tmp.length()-2).concat("}"); //Hiukka rumaa, mut...
        }
        catch (Exception e){ //eeppinen virheiden hallinta, koska ei jaksa iffeillä
            return "";
        }
    }

    //palauttaa bibtex muodossa tiedot, ääkkösiä ei ole hoidettu
    @Override
    public String toString() {
        return("@" + type + "{" + id
        +",\nauthor = " + StringArraytoString(author)
        +",\ntitle = " + StringArraytoString(title)
        +",\njournal = " + StringArraytoString(journal)
        +",\nvolume = " + StringArraytoString(volume)
        +",\nnumber = " + StringArraytoString(number)
        +",\nyear = " + StringArraytoString(year)
        +",\npages = " + StringArraytoString(pages)
        +",\npublisher = " + StringArraytoString(publisher)
        +",\naddress = " + StringArraytoString(address)+",\n}");
    }

    
}
