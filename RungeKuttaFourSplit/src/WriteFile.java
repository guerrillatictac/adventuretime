import java.io.*;

public class WriteFile {
    private String path;
    private boolean append = false;
    
    public WriteFile(String file_path){
        path = file_path;    
    }
    public WriteFile(String file_path, boolean append_value){
        path = file_path;
        append = append_value;
    }
    public void writeToFile(String text) throws IOException{
        FileWriter write = new FileWriter(path, append);
        PrintWriter printLine = new PrintWriter(write);
        printLine.printf("%s" + "%n", text);
        printLine.close();
    }
}
