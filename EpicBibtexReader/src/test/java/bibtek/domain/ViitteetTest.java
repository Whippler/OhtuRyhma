package bibtek.domain;

import java.util.HashSet;
import static org.junit.Assert.*;
import org.junit.Test;

public class ViitteetTest {

    public Viitteet refs;
    public Reference ref1, ref2;

    public ViitteetTest() {
        refs = new Viitteet();
        ref1 = new Reference();
        ref2 = new Reference();
        ref1.setEntryType("book");
        ref1.setId("M99");
        ref1.setField("author", "Mehiläinen, Matti");
        ref1.setField("year", "1999");
        ref1.setField("title", "Kuhnurin elämää");

        ref2.setEntryType("article");
        ref2.setId("VV88");
        ref2.setField("author", "Vainio, Juha and Vanhapoika, Nestori");
        ref2.setField("year", "1988");
        ref2.setField("title", "Öö, Åland");

        refs.add(ref1);
        refs.add(ref2);
    }

    @Test
    public void containsKeyLoytaaKeyt() {
        assertTrue(refs.containsKey("M99"));
        assertTrue(refs.containsKey("VV88"));
    }

    @Test
    public void cointainsKeyPalauttaaFalseJosEiLoydy() {
        assertFalse(refs.containsKey("MM99"));
        assertFalse(refs.containsKey("vv888"));
    }

    @Test
    public void viitteidenTulostusToimii() {
        assertEquals("VV88: Öö, Åland - Vainio, Juha and Vanhapoika, Nestori\n"
                + "M99: Kuhnurin elämää - Mehiläinen, Matti\n", refs.toString());
    }

    @Test
    public void viitteidenTulostusBibtexinaToimii() {
        assertEquals("@article{VV88,\n"
                + "author = {Vainio, Juha and Vanhapoika, Nestori},\n"
                + "title = {\\\"{O}\\\"{o}, {\\AA}land},\n"
                + "year = {1988},\n"
                + "}\n"
                + "\n"
                + "\n"
                + "@book{M99,\n"
                + "author = {Mehil\\\"{a}inen, Matti},\n"
                + "title = {Kuhnurin el\\\"{a}m\\\"{a}\\\"{a}},\n"
                + "year = {1999},\n"
                + "}\n\n\n", refs.viitteetInBibtex());

    }

    @Test
    public void viitteistaHakuToimiiEriParametreilla() {
        String match = refs.haeViitteista("author","Vainio");
        assertEquals("1. Author: Vainio, Juha and Vanhapoika, Nestori\n"
                + "Title: Öö, Åland\n", match);
        
        match = refs.haeViitteista("year","88");
        assertEquals("1. Author: Vainio, Juha and Vanhapoika, Nestori\n"
                + "Title: Öö, Åland\n", match);

        match = refs.haeViitteista("","Kuhnurin");
        assertEquals("1. Author: Mehiläinen, Matti\n"
                + "Title: Kuhnurin elämää\n", match);

        match = refs.haeViitteista("book", "");
        assertEquals("1. Author: Mehiläinen, Matti\n"
                + "Title: Kuhnurin elämää\n", match);
        
        match = refs.haeViitteista("id", "M99");
        assertEquals("1. Author: Mehiläinen, Matti\n"
                + "Title: Kuhnurin elämää\n", match);

    }

    @Test
    public void hakuPalauttaaKaikkiOsumat() {
        String match = refs.haeViitteista("year","19");
        assertEquals("1. Author: Vainio, Juha and Vanhapoika, Nestori\n"
                + "Title: Öö, Åland\n"
                + "2. Author: Mehiläinen, Matti\n"
                + "Title: Kuhnurin elämää\n", match);
    }

    @Test
    public void hakuEiToimiJosVaara() {
        String match = refs.haeViitteista("2009", "dafuq");
        assertEquals("No field/entrytypes found", match);
        match = refs.haeViitteista("title", "Mainio");
        assertEquals("No matches found!", match);
    }

    @Test
    public void addPalauttaaFalseJosViiteOnJoListassa() {
        Reference ref = new Reference();
        ref.setId("M99");
        assertFalse(refs.add(ref));
        ref.setId("VV88");
        assertFalse(refs.add(ref));
    }

    @Test
    public void addToimiiJosViiteEiListassa() {
        Reference ref = new Reference();
        ref.setId("K74");
        ref.setEntryType("booklet");
        ref.setField("author", "Kuutamo, Keijo");
        ref.setField("title", "Yötyö");
        ref.setField("year", "1974");

        assertTrue(refs.add(ref));
        assertTrue(refs.containsKey("K74"));
        assertEquals("VV88: Öö, Åland - Vainio, Juha and Vanhapoika, Nestori\n"
                + "M99: Kuhnurin elämää - Mehiläinen, Matti\n"
                + "K74: Yötyö - Kuutamo, Keijo\n", refs.toString());
    }
}