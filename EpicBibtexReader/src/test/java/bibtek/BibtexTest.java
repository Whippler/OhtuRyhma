package bibtek;

import bibtek.io.IO;
import bibtek.io.InputHandler;
import bibtek.io.StubIO;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Kalle
 */
public class BibtexTest {

    public BibtexTest() {
    }

    @Test
    public void idGenerointiToimii() {
        Bibtex bib = new Bibtex(new StubIO(""));
        //yhdellä
        assertEquals("V23", bib.generoiId("Väinämöinen, Vakavanha", "1023"));
        //usealla
        assertEquals("HM06", bib.generoiId("Hassinen, Marko and Mäyrä, Hannu", "2006"));
    }

    @Test
    public void idGenerointiToimiiVaikkaSamaIDJoOlemassa() {
        IO mockIO = mock(InputHandler.class);
        when(mockIO.fileToString()).thenReturn("@article{VV88,|author = {Vainio, Juha and Vanhapoika, Nestori},|"
                + "title = {Mies ja norppa},|year = {1988},|}|||"
                + "@book{M99,|author = {Mehil\\\"{a}inen, Matti},|"
                + "title = {Kuhnurin el\\\"{a}m\\\"{a}\\\"{a}},|year = {1999},|}|||");
        Bibtex bib = new Bibtex(mockIO);
        bib.lataaViitteet();
        assertEquals("M99a", bib.generoiId("Mehiläinen, Maija", "1999"));
    }

    @Test
    public void idGenerointiToimiiVaikkaUseitaSamojaId() {
        IO mockIO = mock(InputHandler.class);
        when(mockIO.fileToString()).thenReturn("@article{M99,|author = {Mehilainen, Maija},|"
                + "title = {Mies ja norppa},|year = {1999},|}|||"
                + "@book{M99a,|author = {Mehil\\\"{a}inen, Matti},|"
                + "title = {Kuhnurin el\\\"{a}m\\\"{a}\\\"{a}},|year = {1999},|}|||");
        Bibtex bib = new Bibtex(mockIO);
        bib.lataaViitteet();
        assertEquals("M99b", bib.generoiId("Mehilainen, Simo", "1999"));
    }
    
    @Test
    public void idGenerointiPalauttaaTyhjanJosKenttienParametritVaaria(){
        Bibtex bib = new Bibtex(new StubIO(""));
        assertEquals("", bib.generoiId(null, "1999"));
        assertEquals("", bib.generoiId("Mylly, Simo", null));
        assertEquals("", bib.generoiId(null, null));
    }
}
