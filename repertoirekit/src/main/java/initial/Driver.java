package initial;

import java.util.*;

import com.google.common.io.Files;

import java.time.*;
import java.io.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class Driver {

    public static Repertoire book;

    public static void loadBook() {

        book = new Repertoire("My Book", "Vidit Khandelwal");
        File directoryPath = new File("C:\\Users\\vidit\\recipes");
        File filesList[] = directoryPath.listFiles();
        for (int i = 0; i < filesList.length; i++) {
            File currFile = filesList[i];
            loadFile(currFile);
        }
    }

    public static void loadFile(File currFile) {
        String fileName = currFile.getName();
        String recipeName = Files.getNameWithoutExtension(fileName);

        try {
            if (recipeName.equals(Files.asCharSource(currFile, StandardCharsets.UTF_8).readFirstLine())) {
                try {
                    List<String> fileLinesList = Files.asCharSource(currFile, StandardCharsets.UTF_8).readLines();
                    Iterator<String> fileLinesListIter = fileLinesList.iterator();
                    String temp, tempWords[];
                    fileLinesListIter.next();
                    temp=fileLinesListIter.next();
                    Section newSection = book.doesSectionExist(temp);
                    if(newSection==null)
                    {
                        newSection=new Section(temp);
                    }
                    Recipe newRecipe = new Recipe(recipeName, newSection, book, currFile);
                    book.deleteRecipe(newSection, newRecipe);
                    temp = fileLinesListIter.next();
                    tempWords=temp.split(" ");
                    newRecipe.setTime(LocalTime.of(Integer.parseInt(tempWords[0]), Integer.parseInt(tempWords[1]), Integer.parseInt(tempWords[2])));
                    fileLinesListIter.next();
                    temp = fileLinesListIter.next();
                    while (temp.equals("Procedure:") == false && fileLinesListIter.hasNext()) {
                        tempWords = temp.split(" ");
                        String ingredientName = tempWords[0];
                        float quantity = Float.parseFloat(tempWords[1]);
                        Units unit = Enum.valueOf(Units.class, tempWords[2].toUpperCase());
                        newRecipe.addIngredient(ingredientName, quantity, unit);
                        temp = fileLinesListIter.next();
                    }
                    temp = fileLinesListIter.next();
                    while (temp.equals("--endl--") == false && fileLinesListIter.hasNext()) {
                        newRecipe.getProcedure().addStepFromFile(temp);
                        temp = fileLinesListIter.next();
                    }
                } catch (IOException e) {
                    System.err.print("\nIOException in Driver.loadFile");
                    return;
                }
            } else {
                System.err.print("\nRepertoireKit error : File Name does not match Recipe Name or other such error.");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.print("\nNumber Format Exception found.");
        } catch (IOException e) {
            System.out.print("\nIO Exception found.");
        }
            
    }

    public static void main(String[] args) {

        System.out.print("\nPlease wait while RepertoireKit loads...");
        loadBook();
    }
    
    /*public static void main(String[] args) 
    {
        System.out.print("\nPlease wait while RepertoireKit loads...");
        loadBook();
        
        Scanner sc = new Scanner(System.in);
        System.out.print("\nWelcome to Vidit Khandelwal's RepertoireKit Recipe Management System."+
        "\nRepertoireKit is available at https://github.com/csvidit/RepertoireKit under the MIT License");
        while(true)
        {
            String command="", commWords[]=null, secComm="", tertComm="", tertCommWords[]=null, quartComm="", quinComm="", quinCommWords[]=null;
            Recipe currRecipe=null;
            System.out.print("\nEnter command: ");
            command = sc.nextLine();
            command=command.trim()
            commWords=command.split(" ");
            switch(commWords[0])
            {
                case "exit":
                System.out.print("\nBye!");
                break;
                case "search":
                Collection<Recipe> searchResults = book.searchEngine(command.substring(command.indexOf(" ")+1));
                System.out.print("\n"+searchResults.size()+" results were found: \n");
                Iterator<Recipe> searchResultsIter = searchResults.iterator();
                for(int i=1; searchResultsIter.hasNext(); i++)
                {
                    System.out.print("\n"+searchResultsIter+i+". "+searchResultsIter.next().getName());
                }
                searchResultsIter.next();
                System.out.print("\nEnter the number corresponding the recipe you want to see: ");
                secComm=sc.nextLine();
                if(secComm.equalsIgnoreCase("exit") || secComm.equalsIgnoreCase("exit all"))
                {
                    break;
                }
                else
                {
                    int secCommInt=Integer.parseInt(secComm);
                    if(secCommInt>searchResults.size())
                    {
                        System.err.print("\nInvalid index.");
                        break;
                    }
                    else
                    {
                        for(int i=1; searchResultsIter.hasNext(); i++)
                        {
                            if(i==secCommInt)
                            {
                                currRecipe=searchResultsIter.next()
                                System.out.print("\n"+currRecipe.toString());
                                
                            }
                        }
                    }

                }



                case "delete":
                System.out.print("\nAre you sure you want to delete this? (Y/N): ");
                secComm=sc.nextLine();
                if(secComm.equalsIgnoreCase("Y"))
                {
                    book.deleteRecipe(command.substring(command.indexOf(" ",2)+1));
                }
                case "add":
                System.out.print("\nEnter the name of the item: ");
                secComm=sc.nextLine();
                Recipe newRecipe = new Recipe(secComm);
                book.addRecipe(newRecipe);
                currRecipe=newRecipe;
                System.out.print("\nEnter estimated time for this recipe in this format (HH:MM:SS): ");
                tertComm=sc.nextLine();
                currRecipe.addTime(LocalTime.of(Integer.parseInt(tertComm.substring(tertComm.indexOf(":"))), Integer.parseInt(tertComm.substring(tertComm.indexOf(":")+1, tertComm.lastIndexOf(":"))), Integer.parseInt(tertComm.substring(tertComm.lastIndexOf(":")+1))));

                case "edit":
                if(currRecipe==null)
                {
                    secComm=command.substring(command.indexOf(" ",2)+1);
                    currRecipe=book.getByName(secComm);
                }
                /*if(commWords.length>1)
                {
                    secComm=command.substring(command.indexOf(" ",2)+1);
                }*|
                while(true)
                {
                    System.out.print("\nYou are currently in the recipe edit wizard.\nEnter \"exit\" to exit the wizard. Enter \"exit recipe\" to go back to main console. \"exit all\" to close the Recipe System.\nEnter command: ");
                    tertComm=sc.nextLine();
                    tertComm=tertComm.trim();
                    tertCommWords=tertComm.split(" ");
                    switch(tertCommWords[0])
                    {
                        case "enter":
                        case "Enter":
                        case "ENTER":
                        case "eNTER":
                        case "add":
                        case "Add":
                        case "ADD":
                        case "aDD":
                        case "new":
                        case "New":
                        case "NEW":
                        case "nEW":
                        switch(tertCommWords[1])
                        {
                            case "ingredient":
                            case "Ingredient":
                            case "INGREDIENT":
                            case "iNGREDIENT":
                            while(true)
                            {
                                System.out.print("\nYou are currently in the recipe>ingredient edit wizard.\nEnter \"exit\" to go back to the edit wizard. Enter \"exit recipe\" to go back to main console. Enter \"exit all\" to close the Recipe System.\nEnter command: ");
                                quinComm=sc.nextLine();
                                quinComm=quinComm.trim();
                                quinCommWords=quinComm.split(" ");
                                if(quinComm.equalsIgnoreCase("exit"))
                                {
                                    break;
                                }
                                else if(quinComm.equalsIgnoreCase("exit all"))
                                {
                                    command="exit";
                                    break;
                                }
                                else 
                                {
                                    currRecipe.addIngredient(newIngredient);
                                }
                                /*quinComm=sc.nextLine();
                                quinComm=quinComm.trim();
                                quinCommWords=quinComm.split(" ");
                                if(quinComm.equalsIgnoreCase("exit"))
                                {
                                    break;
                                }
                                else if(quinComm.equalsIgnoreCase("exit all"))
                                {
                                    command="exit";
                                    break;
                                }
                                else 
                                {
                                    Ingredient newIngredient = new Ingredient(quinCommWords[0]);
                                    Quantity newQuantity = new Quantity(newIngredient, Float.parseFloat(quinCommWords[1]), Enum.valueOf(Units.class, quinCommWords[2].toUpperCase()));
                                    book.addIngredient(newIngredient, currRecipe);
                                    currRecipe.addIngredient();
                                    
                                }*|
                                

                            }
                        }
                    }
                }
            }
            if(command.equalsIgnoreCase("exit"))
            {
                break;
            }
        }

    }*/

}
