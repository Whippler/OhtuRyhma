package bibtek;

import bibtek.domain.Reference;
import bibtek.domain.Viitteet;
import bibtek.io.IO;
import java.util.Arrays;
import java.util.HashSet;

public class Bibtex {

    private IO io;
    private Reference ref;
    private Viitteet references;

    public Bibtex(IO io) {
        this.io = io;
        references = new Viitteet();
    }

    //luo uuden viitteen, kysyy tyypin ja sisällön
    public void createReference() {
        String inp;
        this.ref = new Reference();
        io.print("Give the type of your entry\n\n"+Arrays.toString(ref.entrytypes));
        while (true) {    //kysytään käyttäjältä minkä tyyppinen viite luodaan
            inp = io.readUserInput(">");
            if (ref.setEntryType(inp)) { //jos käyttäjän syöttämä viitteen tyyppi hyväksytään
                break;
            }
            io.print("Entry type not valid\n");
        }
        io.print("Give the name of the field, press enter, then give content.\n"
                + "Hit enter instead of typing in a field when you are done\n\n"
                +Arrays.toString(ref.fieldtypes)+"\n Author, Year and Title are required");
        String field;
        while (true) {    //kysytään ensin mikä kenttä, sen jälkeen kentän sisältö
            field = io.readUserInput(">");
            if (field.equals("") && ref.checkIfValid()) {
                break;
            }
            if (field.equals("")) {
                io.print("Author, Year and Title are required");
                continue;
            }
            if(field.toLowerCase().equals("author")) io.print("Author is presented in format: Firstname, Surrname & ...");
            inp = io.readUserInput(field + ":");
            if (!ref.setField(field.toLowerCase(), inp)) {
                io.print("Invalid input! Check that the field is written correctly and the content is not empty.");
            }
            
        }

        String newID = generoiId(ref.getData().get("author"), ref.getData().get("year"));
        ref.setId(newID);
        Character alku = 'a';
        int a = alku.charValue();
        references.add(ref);
    }

    private String generoiId(String author, String year) {
        if(author == null || year == null){
            return "";
        }
        String retID = "";
        for (String s : author.split(" and ")) {
            retID += s.charAt(0);
        }
        retID += year.substring(year.length() - 2);
        
        
        Character alku = 'a';
        int a = alku.charValue();

        while (true) {  // tarkistaa onko ID jo käytössä ja jos on niin vaihtaa viimeisen merkin uuteen.
            if (references.containsKey(retID)) {
                retID = retID.substring(0, retID.length() - 2);

                retID = retID + (char) a;
                a++;
            } else {
                break;
            }
        }
        return retID;
    }

    public void lataaViitteet() {
        String s = io.fileToString();
        String[] lines = s.split("\\|");
        String id = "";
        Reference readedref = new Reference();

        for (String currentLine : lines) {
            if (currentLine.isEmpty()) {
                continue;
            } else if (currentLine.charAt(0) == '@') {
                String[] t = currentLine.split("\\{");
                readedref.setEntryType(t[0].substring(1));
                id = t[1].substring(0, t[1].length() - 1);
                readedref.setId(id);
            } else if (currentLine.charAt(0) == '}') {
                references.add(readedref);
                readedref = new Reference();
            } else {
                String[] fields = currentLine.split(" = ");
                String data = readedref.korjaaKirjaimet(fields[1]).replaceAll("([\\{}])", "");
                data = data.substring(0, data.length() - 1);
                readedref.setField(fields[0], data);
            }
        }

    }

    public void run() {
        io.selectFile();
        lataaViitteet();
        String input;
        while (true) {  //sitten aletaan käsittelemään muita syötteitä

            if (ref != null) {
                io.print("Current ref: " + ref.getData().get("title"));
            }
            io.print("Enter create if you want to create a reference.\n"
                    + "Enter search if you want to search from files.\n"
                    + "Enter plain if you want to read reference in plaintext.\n"
                    + "Enter bib if you want to read reference in bibtex.\n"
                    + "Enter list to list all references\n"
                    + "Enter biblist to list all references in bibtex.\n"
                    + "Enter select if you want to select a certain reference.\n"
                    + "Enter save if you want to save the references to a file.\n"
                    + "Press enter if you want to quit.\n");
            input = io.readUserInput(">");
            if (input.equalsIgnoreCase("create")) {
                createReference();
                if (this.ref == null || this.ref.refInBibtex() == null) {
                    io.print("Error in creating bibtex reference!\n"
                            + "Remember that the required fields are: author, year and title\n");
                } else {
                    io.print("Created new reference!");
                }
            } else if (input.equalsIgnoreCase("save")) {
                if (this.references == null || this.references.viitteetInBibtex() == null) {
                    io.print("No current reference to save!");
                } else {
                   input =  io.readUserInput("Define save location, or leave empty to write loaded file\n"
                           + "The whole path must be presented, in Linux ex. /home/user/folder/filename.bib\n"
                           + "in Windows, ex. C:/Users/user/Documents/folder/file.bib:\n\n");
                    if (io.saveRefstoFile(references.viitteetInBibtex(), input)) {
                        io.print("References added it to file\n");
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
                io.print("Bye!\n");
                break;
            } else if (input.equals("list")) {
                io.print(references.toString());
            } else if (input.equals("biblist")) {
                io.print(references.viitteetInBibtex());
            } else if (input.equals("search")) {
                HashSet<Reference> match = references.haeViitteista(io.readUserInput("Mitä haetaan?"));
                if (match == null) {
                    io.print("No matches!");
                } else {
                    io.print(match.toString());
                }
            } else if (input.equals("select")) {
                io.print(references.toString() + "----------------------");
                ref = references.getViitteet().get(io.readUserInput("kirjoita id"));
            }
        }
    }
}
