package bibtek.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//POistin IO rajapinnan käytöstä koska laiska
public class InputHandler implements IO {

    private File refs;
    private Scanner sc;

    public InputHandler(Scanner s) {
        this.sc = s;
    }

    public InputHandler() {
        this(new Scanner(System.in));
    }

    @Override
    public String readUserInput(String prompt) {
        System.out.print(prompt + " ");
        return sc.nextLine();
    }

    @Override
    public void print(String s) {
        System.out.println(s);
    }

    //luo tiedoston johon viitteet tallennetaan, mikäli ei aiemmin ollut olemassa
    @Override
    public void initBibtexFile(File file) {
        this.refs = file;
        try {
            if (!this.refs.exists()) {
                this.refs.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    //tallentaa tiedot yhteen refs.bib -tiedostoon
    @Override
    public boolean saveRefstoFile(String text, String path) {
        if (this.refs == null) {
            return false;
        }
        String refOld = refs.getAbsolutePath();
        try {
            if (!path.isEmpty()) {
                refs = new File(path + ".bib");
                refs.createNewFile();
            }
            PrintWriter bw = new PrintWriter(refs); 
            bw.print("");
            bw.write(text);
            bw.close();
            if (path.isEmpty()) {
                refs = new File(refOld);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    @Override
    public void selectFile() {
        String input;
        print("By default your reference file name is refs.bib.\n"
                + "Do you wish to change this setting? (y/n)\n");

        while (refs == null || !refs.exists()) {  //ensin kysytään käyttäjältä mihin tiedostoon tallennetaan
            input = readUserInput(">");
            if (input.equalsIgnoreCase("n")) {
                this.initBibtexFile(new File("refs.bib"));
                break;
            } else if (input.equalsIgnoreCase("y")) {
                input = "";
                while (input.length() == 0) {
                    input = readUserInput("filename:");
                    if (input.isEmpty()) {
                        continue;
                    }
                    this.initBibtexFile(new File(input + ".bib"));
                    print("References will now be saved into " + input + ".bib");
                }
            } else {
                continue;
            }
        }
    }

    @Override
    public String fileToString() {
        try {
            Scanner sca = new Scanner(refs);
            String tmp = "";
            while (sca.hasNextLine()) {
                tmp += sca.nextLine();
                tmp += "|";
            }
            return tmp;
        } catch (FileNotFoundException ex) {
            return "file not found";
        }
    }
}
