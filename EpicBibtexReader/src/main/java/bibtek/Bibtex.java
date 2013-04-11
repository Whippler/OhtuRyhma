package bibtek;

import bibtek.domain.Reference;
import bibtek.io.IO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Bibtex {

    private File refs;
    private IO io;
    private Reference ref;

    public Bibtex(IO io) {
        this.io = io;
        this.refs = null;
    }

    //luo tiedoston johon viitteet tallennetaan, mikäli ei aiemmin ollut olemassa
    public void initBibtexFile(String filename) {
        this.refs = new File(filename + ".bib");
        try {
            if (!this.refs.exists()) {
                this.refs.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    //tallentaa tiedot yhteen refs.bib -tiedostoon
    public boolean tallennaTiedot() {
        if (this.refs == null) {
            return false;
        }
        try {
            FileWriter fw = new FileWriter(this.refs.getName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(ref.refInBibtex());
            bw.close();
            fw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    //luo uuden viitteen, kysyy tyypin ja sisällön
    public void createReference() {
        String inp;
        this.ref = new Reference();
        io.print("Give the type of your entry\n");
        while (true) {    //kysytään käyttäjältä minkä tyyppinen viite luodaan
            inp = io.readUserInput(">");
            if (ref.setEntryType(inp)) { //jos käyttäjän syöttämä viitteen tyyppi hyväksytään
                break;
            }
            io.print("Entry type not valid\n");
        }
        io.print("Give the name of the field, press enter, then give content.\n"
                + "Hit enter instead of typing in a field when you are done\n");
        String field;
        while (true) {    //kysytään ensin mikä kenttä, sen jälkeen kentän sisältö
            field = io.readUserInput(">");
            if (field.equals("")) {
                break;
            }
            inp = io.readUserInput(field + ":");
            if (!ref.setField(field, inp)) {
                io.print("Invalid input! Check that the field is written correctly and the content is not empty.");
            }
        }
    }

  
    public void run() {
        String input;
        io.print("By default your reference file name is refs.bib.\n"
                + "Do you wish to change this setting? (y/n)\n");

        while (true) {  //ensin kysytään käyttäjältä mihin tiedostoon tallennetaan
            input = io.readUserInput(">");
            if (input.equalsIgnoreCase("n")) {
                this.initBibtexFile("refs");
                break;
            } else if (input.equalsIgnoreCase("y")) {
                input = "";
                while (input.length() == 0) {
                    input = io.readUserInput("filename:");
                    this.initBibtexFile(input);
                }
            }
            break;
        }

        while (true) {  //sitten aletaan käsittelemään muita syötteitä
            io.print("Enter create if you want to create reference.\n"
                    + "Enter plain if you want to read reference in plaintext.\n"
                    + "Enter bib if you want to read reference in bibtex.\n"
                    + "Enter save if you want to save the current reference to a file.\n"
                    + "Press enter if you want to quit.\n");
            input = io.readUserInput(">");
            if (input.equalsIgnoreCase("create")) {
                createReference();
                if (this.ref == null || this.ref.refInBibtex() == null) {
                    io.print("Error in creating bibtex reference!\n"
                            + "Remember that the required fields are: author, year and title\n");
                }
            } else if (input.equalsIgnoreCase("save")) {
                if (this.ref == null || this.ref.refInBibtex() == null) {
                    io.print("No current reference to save!");
                } else {
                    if (this.tallennaTiedot()) {
                        io.print("Created a new reference and added it to file\n");
                    } else {
                        io.print("Error in writing to a file!");
                    }
                }
            } else if (input.equalsIgnoreCase("plain")) {
                if (this.ref == null || this.ref.refInBibtex() == null) {
                    io.print("No current reference to view!\n");
                } else {
                    io.print(ref.refInPlainText());
                }

            } else if (input.equalsIgnoreCase("bib")) {
                if (this.ref == null || this.ref.refInBibtex() == null) {
                    io.print("No current reference to view!\n");
                } else {
                    io.print(ref.refInBibtex());
                }

            } else if (input.equals("")) {
                break;
            }
        }
    }
}
