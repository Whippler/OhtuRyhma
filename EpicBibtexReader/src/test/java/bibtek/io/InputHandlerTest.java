package bibtek.io;

import bibtek.domain.Reference;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InputHandlerTest {

    InputHandler io;
    File testi;
    // public InputHandlerTest() {
    // }

    private void luoTestiTiedosto() throws IOException {
        String sisalto = "@article{VV88,\n"
                + "author = {Vainio, Juha and Vanhapoika, Nestori},\n"
                + "title = {Mies ja norppa},\n"
                + "year = {1988},\n"
                + "}\n"
                + "\n"
                + "\n"
                + "@book{M99,\n"
                + "author = {Mehil\\\"{a}inen, Matti},\n"
                + "title = {Kuhnurin el\\\"{a}m\\\"{a}\\\"{a}},\n"
                + "year = {1999},\n"
                + "}\n\n\n";
        testi = new File("bibtesti2587205.bib");
        testi.createNewFile();
        PrintWriter bw = new PrintWriter(testi);
        bw.write(sisalto);
        bw.close();
    }

    private void initBibtexFileTestit(boolean tdstoOlemassa, int createNewFileKutsut) {
        File mockFilu = mock(File.class);
        when(mockFilu.exists()).thenReturn(tdstoOlemassa);
        io = new InputHandler();
        io.initBibtexFile(mockFilu);


        verify(mockFilu, times(1)).exists();
        try {
            verify(mockFilu, times(createNewFileKutsut)).createNewFile();
        } catch (IOException e) {
        }
    }

    @Test
    public void initBibtexFileLuoUudenJosTiedostoEiOlemassa() {
        initBibtexFileTestit(false, 1);
    }

    @Test
    public void initBibtexFileEiLuoUuttaJosTdstoOlemassa() {
        initBibtexFileTestit(true, 0);
    }

    @Test
    public void fileToStringToimii() throws FileNotFoundException, IOException {
        //luoTestiTiedosto();
        io = new InputHandler();
        testi = new File("bibtesti2587205.bib");
        io.initBibtexFile(testi);
        assertEquals("@article{VV88,|author = {Vainio, Juha and Vanhapoika, Nestori},|"
                + "title = {Mies ja norppa},|year = {1988},|}|||"
                + "@book{M99,|author = {Mehil\\\"{a}inen, Matti},|"
                + "title = {Kuhnurin el\\\"{a}m\\\"{a}\\\"{a}},|year = {1999},|}|||", io.fileToString());
    }

    @Test
    public void saveRefsToFileToimii() throws IOException {
        io = new InputHandler();
        File testi2 = new File("bibtesti3482956");
        
        testi2.createNewFile();
        io.initBibtexFile(testi2);
        Reference ref = new Reference();
        ref.setEntryType("book");
        ref.setId("K04");
        ref.setField("author", "Kala, Hauki");
        ref.setField("title", "Kalapuikot ja muut reseptit");
        ref.setField("year", "2004");
        io.saveRefstoFile(ref.refInBibtex(), testi2.getAbsolutePath());

        Scanner sc = new Scanner(new File("bibtesti3482956.bib"));
        String sisalto = "";
        while (sc.hasNextLine()) {
            sisalto += "" + sc.nextLine();
        }
        assertEquals("@book{K04,"
                + "author = {Kala, Hauki},"
                + "title = {Kalapuikot ja muut reseptit},"
                + "year = {2004},"
                + "}", sisalto);
    }
     
}
