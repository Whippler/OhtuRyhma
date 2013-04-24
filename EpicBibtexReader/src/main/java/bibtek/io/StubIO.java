package bibtek.io;

import java.io.File;
import java.util.ArrayList;

public class StubIO implements IO {

    private String filename;
    private String[] inputs;
    private int i;
    private ArrayList<String> prints;

    public StubIO(String... inputs) {
        this.inputs = inputs;
        this.prints = new ArrayList<String>();
        this.i = 0;
        this.filename = "";
    }

    @Override
    public String readUserInput(String prompt) {
        print(prompt);
        if (i < inputs.length) {
            return inputs[i++];
        }
        return "";
    }

    @Override
    public void print(String s) {
        prints.add(s);
    }

    @Override
    public boolean saveRefstoFile(String s, String path) {
        return true;
    }

    @Override
    public void selectFile(String filename) {
        this.filename = filename + ".bib";
        initBibtexFile(new File(this.filename));
    }

    @Override
    public void initBibtexFile(File file) {
        print("References will now be saved into " + file.getName());
    }

    public ArrayList<String> getPrints() {
        return this.prints;
    }

    @Override
    public String fileToString() {
        if (this.filename.equals("refs.bib")) {
            return "@article{VV88,|author = {Vainio, Juha and Vanhapoika, Nestori},|"
                    + "title = {Mies ja norppa},|year = {1988},|}|||"
                    + "@book{M99,|author = {Mehil\\\"{a}inen, Matti},|"
                    + "title = {Kuhnurin el\\\"{a}m\\\"{a}\\\"{a}},|year = {1999},|}|||";
        } else {
            return "";
        }
    }
}
