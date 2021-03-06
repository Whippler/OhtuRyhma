package bibtek.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class InputHandler implements IO {

    public File refs;
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
        if (file == null) {
            return;
        }
        this.refs = file;
        try {
            if (!this.refs.exists()) {
                this.refs.createNewFile();
            }
        } catch (IOException e) {
            print("Error when creating a file!");
        //    e.printStackTrace(System.out);
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
         //   e.printStackTrace(System.out);
            return false;
        }
    }

    @Override
    public void selectFile(String filename) {
        if (filename != null && !filename.equals("")) {
            this.initBibtexFile(new File(filename + ".bib"));
        }
    }

    @Override
    public String fileToString() {
        if (this.refs == null) {
            return "file not found";
        }
        String tmp = "";
        try {
            Scanner sca = new Scanner(refs);
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
