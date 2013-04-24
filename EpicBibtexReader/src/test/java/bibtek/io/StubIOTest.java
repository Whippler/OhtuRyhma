package bibtek.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class StubIOTest {

    StubIO io;

    public StubIOTest() {
    }

    @Test
    public void readUserInputPalauttaaKaikkiSyotteet() {
        io = new StubIO("n", "", "asd", "helo", "möö");
        String syotteet = "";
        for(int i = 0; i < 5; i++){
            syotteet += "" + io.readUserInput("");
        }
        assertEquals("nasdhelomöö", syotteet);
    }
    
    @Test
    public void printJaGetPrintsToimivat(){
         io = new StubIO("");
         io.print("jotain");
         io.print("tähän");
         io.print("ja tohon");
         assertEquals("jotain", io.getPrints().get(0));
         assertEquals("tähän", io.getPrints().get(1));
         assertEquals("ja tohon", io.getPrints().get(2));
    }
    
    @Test
    public void selectFileToimiiJosHalutaanVaihtaaNimea(){
        io = new StubIO("y", "asd.bib");
        io.selectFile("asd");
        assertEquals("References will now be saved into asd.bib", io.getPrints().get(io.getPrints().size()-1));
    }
}
