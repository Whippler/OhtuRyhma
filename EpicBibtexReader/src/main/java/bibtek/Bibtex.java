package bibtek;

import bibtek.domain.Reference;
import bibtek.domain.Viitteet;
import bibtek.io.IO;

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

        String newID = generoiId(ref.getData().get("author"), ref.getData().get("year"));

        Character alku = 'a';
        int a = alku.charValue();

        while (true) {  // tarkistaa onko ID jo käytössä ja jos on niin vaihtaa viimeisen merkin uuteen.
            if (references.containsKey(newID)) {
                newID = newID.substring(0, newID.length() - 2);

                newID = newID + (char) a;
                a++;
            } else {
                references.add(newID, ref);
                break;
            }
        }

    }

    private String generoiId(String author, String year) {
        String retID = "";
        for (String s : author.split(" and ")) {
            retID += s.charAt(0);
        }
        retID += year.substring(year.length() - 2);
        return retID;
    }

    public void lataaViitteet() {
//@article{W04,
//journal = {J. Comput. Small Coll.},
//author = {Whittington, Keith\"{o}\"{o}\"{A}P\{AA}\"{o}\"{A} J.},
//title = {Infusing active learning into introductory programming courses},
//address = {USA},
//pages = {249--259},
//volume = {19},
//year = {2004},
//number = {5},
//publisher = {Consortium for Computing Sciences in Colleges},
//}
        String s = io.fileToString();
        String[] lines = s.split("\\|");
        String id = "";
        Reference readedref= new Reference();
        
        for(String currentLine : lines) {
            if (currentLine.isEmpty()) continue;
            else if(currentLine.charAt(0)=='@'){
                String[] t = currentLine.split("\\{");
                readedref.setEntryType(t[0].substring(1));
                id = t[1].substring(0,t[1].length()-1);
                readedref.setId(id);
                System.out.println(id);

            }
            else if (currentLine.charAt(0)=='}'){
                references.add(id, ref);
                readedref=new Reference();
            }
            
        }
        
        System.exit(0);
    }

    public void run() {
        lataaViitteet();

        String input;
        while (true) {  //sitten aletaan käsittelemään muita syötteitä
            io.print("Enter create if you want to create a reference.\n"
                    + "Enter select if you want to find/select one from files.\n"
                    + "Enter plain if you want to read reference in plaintext.\n"
                    + "Enter bib if you want to read reference in bibtex.\n"
                    + "Enter list to list all references"
                    + "Enter save if you want to save the references to a file.\n"
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
                    if (io.saveRefstoFile(ref.toString())) {
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
