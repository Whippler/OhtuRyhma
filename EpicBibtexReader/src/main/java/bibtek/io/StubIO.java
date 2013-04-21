package bibtek.io;

import java.io.File;
import java.util.ArrayList;

public class StubIO implements IO {

    private String[] inputs;
    private int i;
    private ArrayList<String> prints;

    public StubIO(String... inputs) {
        this.inputs = inputs;
        this.prints = new ArrayList<String>();
        this.i = 0;
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
    public void selectFile() {
        String in = readUserInput(">");
        if (in.equalsIgnoreCase("y")) {
            String fn = readUserInput("filename: ");
            initBibtexFile(new File(fn));
        }
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
        return "";
    }
}