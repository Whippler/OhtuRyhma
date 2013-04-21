package bibtek;

import bibtek.io.IO;
import bibtek.io.InputHandler;
import java.util.Scanner;

public class Main {

    public static void main(String Args[]) {
        IO io = new InputHandler(new Scanner(System.in));
        Bibtex bib = new Bibtex(io);
        bib.run();
    }
}