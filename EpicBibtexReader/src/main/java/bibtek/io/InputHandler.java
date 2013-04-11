package bibtek.io;

import java.util.Scanner;

public class InputHandler implements IO {

    private Scanner sc;

    public InputHandler() {
        this.sc = new Scanner(System.in);
    }

    public String readUserInput(String prompt) {
        System.out.print(prompt + " ");
        return sc.nextLine();
    }

    public void print(String s) {
        System.out.println(s);
    }
}
