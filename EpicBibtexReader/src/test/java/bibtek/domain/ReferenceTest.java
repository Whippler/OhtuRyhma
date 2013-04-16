package bibtek.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ReferenceTest {

    Reference ref;

    public ReferenceTest() {
        ref = new Reference();
    }

    public void laitaSyote() {
        ref.setEntryType("article");
        ref.setField("author", "Väinämöinen, Vakavanha");
        ref.setField("title", "Kantele A:sta Ö:hön");
        ref.setField("journal", "Örkki");
        ref.setField("volume", "2");
        ref.setField("number", "1");
        ref.setField("year", "1023");
        ref.setField("pages", "0--450");
        ref.setField("publisher", "Louhi media");
        ref.setField("address", "Kalevala");
        ref.setId("V23");
    }

    @Test
    public void setEntryTypeHylkaaVaaran() {
        assertFalse(ref.setEntryType(null));
        assertFalse(ref.setEntryType("articcle"));
        assertFalse(ref.setEntryType("inproceeding"));
        assertFalse(ref.setEntryType("booklett"));
    }

    @Test
    public void setEntryHyvaksyyOikean() {
        assertTrue(ref.setEntryType("article"));
        assertTrue(ref.setEntryType("booKlet"));
        assertTrue(ref.setEntryType("inPROCEedings"));
        assertTrue(ref.setEntryType("PHDTHESIS"));
    }

    @Test
    public void MuuttuukoBibtexMuodoksiTest() {
        laitaSyote();
        String muoto = ref.refInBibtex();

        assertEquals("@article{V23,\n"
                + "author = {V\\\"{a}in\\\"{a}m\\\"{o}inen, Vakavanha},\n"
                + "title = {Kantele A:sta \\\"{O}:h\\\"{o}n},\n"
                + "journal = {\\\"{O}rkki},\n"
                + "address = {Kalevala},\n"
                + "pages = {0--450},\n"
                + "volume = {2},\n"
                + "year = {1023},\n"
                + "number = {1},\n"
                + "publisher = {Louhi media},\n"
                + "}\n\n", muoto);

    }

    @Test
    public void SelkokieliTest() {
        laitaSyote();
        String selkokieli = ref.refInPlainText();

        assertEquals("\n"
                + "author: Väinämöinen, Vakavanha\n"
                + "title: Kantele A:sta Ö:hön\n"
                + "journal: Örkki\n"
                + "address: Kalevala\n"
                + "pages: 0--450\n"
                + "volume: 2\n"
                + "year: 1023\n"
                + "number: 1\n"
                + "publisher: Louhi media\n", selkokieli);
    }

    @Test
    public void SkandiTesti() {
        boolean tarkistaja = false;
        String mjono = ref.korjaaKirjaimet("äadfsÖÄÅ saföd asöä äöädfh ÄÖP ÖAdfghS Äådg åÅads");

        for (int i = 0; i < mjono.length(); i++) {
            char k = mjono.toString().charAt(i);
            if (k == 'ä' || k == 'ö' || k == 'Ä' || k == 'Ö' || k == 'å' || k == 'Å') {
                tarkistaja = true;
            }
        }
        assertFalse(tarkistaja);
    }

//    @Test
//    public void IDTesti() {
//        String id = ref.generoiId("Väinämöinen, Vakavanha", "1023");
//        assertEquals("V23", id);
//
//    }
//
//    @Test
//    public void IDTestiUseallaKirjoittajalla() {
//        String id = ref.generoiId("Hassinen, Marko and Mäyrä, Hannu", "2006");
//        assertEquals("HM06", id);
//    }
}
