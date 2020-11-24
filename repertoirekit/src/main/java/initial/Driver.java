package initial;

import java.util.*;

import com.google.common.io.Files;

import java.time.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Driver {

    public static Repertoire book;

    public static void loadBook() {

        book = new Repertoire("My Book", "Vidit Khandelwal");
        File directoryPath = new File("C:\\Users\\vidit\\recipes");
        File filesList[] = directoryPath.listFiles();
        for (int i = 0; i < filesList.length; i++) 
        {
            File currFile = filesList[i];
            loadFile(currFile);
        }
    }

    public static void loadFile(File currFile)
    {
        if(Files.isReadable())
        {
            String fileName = currFile.getName();
            String recipeName = Files.getNameWithoutExtension(fileName);

            if(recipeName.equals(Files.asCharSource(currFile, StandardCharsets.UTF_8).readFirstLine()))
            {
                Recipe newRecipe = new Recipe(recipeName, book);
                try 
                {
                    List<String> fileLinesList = Files.asCharSource(currFile, StandardCharsets.UTF_8).readLines();
                    Iterator<String> fileLinesListIter = fileLinesList.iterator();
                    fileLinesListIter.next();
                    fileLinesListIter.next();
                    fileLinesListIter.next();
                    while(fileLinesListIter.hasNext())
                    {
                        String temp=fileLinesListIter.next();
                        String tempWords[]=temp.split(" ");
                        while(temp.equals("Procedure: ")==false)
                        {
                            String ingredientName=tempWords[0];
                            float quantity=Float.parseFloat(tempWords[1]);
                            Units unit=Enum.valueOf(Units.class, tempWords[2].toUpperCase());
                            newRecipe.addIngredient(ingredientName, quantity, unit);
                        }
                        while(temp.equals("--endl--")==false)
                        {
                            newRecipe.setToProcedure().addStepFromFile(temp);
                        }
                    }
                } 
                catch (IOException e) 
                {
                    System.err.print("\nIOException in Driver.loadFile");
                    return;
                }
            }
            else
            {
                System.err.print("\nRepertoireKitSystem error : File Name does not match Recipe Name or other such error.");
                return;
            }
            
        }
    }
    
    public static void main(String[] args) 
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
                switch(commWords[1])
                {
                    case "ingredient":
                    case "Ingredient":
                    case "INGREDIENT":
                    case "iNGREDIENT":
                    System.out.print("\n"+book.getByIngredient(commWords[2]).toString());
                    System.out.print("\nEnter the name of the recipe you want to see: ");
                    secComm=sc.nextLine();
                    if(secComm.equalsIgnoreCase("exit"))
                    {
                        break;
                    }
                    else
                    {
                        System.out.print("\n"+book.getByName(secComm).toString());
                    }
                    break;
                    case "recipe":
                    case "Recipe":
                    case "RECIPE":
                    case "rECIPE":
                    System.out.print("\n"+book.getByName(command.substring(command.indexOf(" ",2)+1)).toString());
                    break;
                    case "section":
                    case "Section":
                    case "SECTION":
                    case "sECTION":
                    System.out.print("\n"+book.getBySection(commWords[2]).toString());
                    System.out.print("\nEnter the name of the recipe you want to see: ");
                    secComm=sc.nextLine();
                    if(secComm.equalsIgnoreCase("exit"))
                    {
                        break;
                    }
                    else
                    {
                        System.out.print("\n"+book.getByName(secComm).toString());
                        currRecipe=book.getByName(secComm);
                    }
                    break;
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
                }*/
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
                                    
                                }*/
                                

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

    }

}
