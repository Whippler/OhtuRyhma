package bibtek;

import bibtek.io.IO;
import bibtek.io.InputHandler;

public class Main {

    public static void main(String Args[]) {
        IO io = new InputHandler();
//        io.selectFile();
        Bibtex bib = new Bibtex(io);
        bib.run();
    }
}