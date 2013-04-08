package bibtek;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BibtexTest {

    @Test
    public void MuuttuukoBibtexMuodoksiTest() {

        String[] artikkeli = {"article", "Väinämöinen, Vakavanha", "Kantele A:sta Ö:hön", "Örkki", "2", "1", "1023", "0--450", "Louhi media", "Kalevala"};

        Bibtex btx = new Bibtex(artikkeli);

        String muoto = btx.toString();

        assertEquals("@article{V23,\n"
                + "journal = {\\\"{O}rkki},\n"
                + "author = {V\\\"{a}in\\\"{a}m\\\"{o}inen, Vakavanha},\n"
                + "title = {Kantele A:sta \\\"{O}:h\\\"{o}n},\n"
                + "address = {Kalevala},\n"
                + "pages = {0--450},\n"
                + "volume = {2},\n"
                + "year = {1023},\n"
                + "number = {1},\n"
                + "publisher = {Louhi media},\n"
                + "}\n\n", muoto);


    }

    @Test
    public void IDTesti() {

        String[] artikkeli = {"article", "Väinämöinen, Vakavanha", "Kantele A:sta Ö:hön", "Örkki", "2", "1", "1023", "0--450", "Louhi media", "Kalevala"};

        Bibtex btx = new Bibtex(artikkeli);

        String bibtexMuoto = btx.toString();

        String id = bibtexMuoto.substring(9, 12);
        assertEquals("V23", id);

    }

    @Test
    public void IDTestiUseallaKirjoittajalla() {
        String[] artikkeli = {"article", "Hassinen, Marko and Mäyrä, Hannu", "fdasd", "add", "456", "5464", "2006", "2--250", "media", "Neuvostoliitto"};

        Bibtex btx = new Bibtex(artikkeli);

        String bibtexMuoto = btx.toString();

        String id = bibtexMuoto.substring(9, 13);
        assertEquals("HM06", id);
    }

    @Test
    public void SkandiTesti() {
        String[] artikkeli = {"article", "Väinämöinen, Vakavanha", "Kantele A:sta Ö:hön", "Örkki", "2", "1", "1023", "0--450", "Louhi media", "Kalevala"};

        Bibtex btx = new Bibtex(artikkeli);

        boolean tarkistaja = false;

        for (int i = 0; i < btx.toString().length(); i++) {
            char k = btx.toString().charAt(i);
            if (k == 'ä' || k == 'ö' || k == 'Ä' || k == 'Ö' || k == 'å' || k == 'Å') {
                tarkistaja = true;
            }
        }

        assertFalse(tarkistaja);
    }

    @Test
    public void SelkokieliTest() {
        String[] artikkeli = {"article", "Väinämöinen, Vakavanha", "Kantele A:sta Ö:hön", "Örkki", "2", "1", "1023", "0--450", "Louhi media", "Kalevala"};
        Bibtex btx = new Bibtex(artikkeli);

        String selkokieli = btx.tulostaSelkokielellä();

        assertEquals("\n"
                + "journal: Örkki\n"
                + "author: Väinämöinen, Vakavanha\n"
                + "title: Kantele A:sta Ö:hön\n"
                + "address: Kalevala\n"
                + "pages: 0--450\n"
                + "volume: 2\n"
                + "year: 1023\n"
                + "number: 1\n"
                + "publisher: Louhi media\n", selkokieli);
    }
}