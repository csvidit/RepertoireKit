package initial;

import java.util.*;
import java.time.*;
import java.io.*;

public class Driver {
    
    public static void main(String[] args) 
    {
        Repertoire book = new Repertoire("Croquembouche", "Vidit Khandelwal");
        Scanner sc = new Scanner(System.in);
        System.out.print("\nWelcome to Vidit Khandelwal's RecipeKit Recipe Management System.");
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
