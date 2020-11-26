package initial;

import java.util.*;
import java.io.*;

public class TestFiles {
    
    public static void main(String[] args) {
        
        File directoryPath = new File("C:\\Users\\vidit\\recipes");
        File filesList[] = directoryPath.listFiles();
        String name = filesList[0].getName();

    }

}
