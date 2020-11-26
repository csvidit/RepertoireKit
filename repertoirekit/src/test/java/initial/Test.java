package initial;

import java.util.*;
import java.io.*;

public class Test {

    public static void main(String[] args) {

        ListMultimap<String, Integer> treeListMultimap =
    MultimapBuilder.treeKeys().arrayListValues().build();
    
        File myFile = new File("myFile.txt");
        try {
            myFile.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        FileWriter writer;
        try {
            writer = new FileWriter(myFile);
            try {
                writer.write("Apple\nMicrosoft\nGoogle\nNetflix");
                writer.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Scanner sc;
            try {
                sc = new Scanner(myFile);
                String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.equals("Microsoft")) {
                    try {
                        writer.write("\nAmazon");
                        writer.flush();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
}
