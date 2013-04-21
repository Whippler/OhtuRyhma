package bibtek.io;

import java.io.File;

public interface IO {
    
    String readUserInput(String prompt);

    void print(String s);
    
    boolean saveRefstoFile(String s, String path);
    
    void selectFile();
    
    String fileToString();
    
    void initBibtexFile(File file);
    
}
