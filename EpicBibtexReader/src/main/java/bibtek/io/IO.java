package bibtek.io;

public interface IO {
    
    String readUserInput(String prompt);

    void print(String s);
    
    boolean saveRefstoFile(String s, String path);
    
    void selectFile();
    
    String fileToString();
    
    void initBibtexFile(String filename);
    
}
