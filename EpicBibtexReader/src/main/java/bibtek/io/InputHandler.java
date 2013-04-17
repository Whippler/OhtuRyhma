package bibtek.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


//POistin IO rajapinnan käytöstä koska laiska
public class InputHandler implements IO {

    private File refs;
    private Scanner sc;

    public InputHandler() {
        this.sc = new Scanner(System.in);
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
    @Override
    public boolean saveRefstoFile(String s) {
        if (this.refs == null) {
            return false;
        }
        try {
            FileWriter fw = new FileWriter(this.refs.getName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);
            bw.close();
            fw.close();
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

        while (true) {  //ensin kysytään käyttäjältä mihin tiedostoon tallennetaan
            input = readUserInput(">");
            if (input.equalsIgnoreCase("n")) {
                this.initBibtexFile("refs");
                break;
            } else if (input.equalsIgnoreCase("y")) {
                input = "";
                while (input.length() == 0) {
                    input = readUserInput("filename:");
                    this.initBibtexFile(input);
                    print("References will now be saved into " + input + ".bib");
                }
            }
            break;
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
