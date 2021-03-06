package initial;

import java.util.*;
import java.time.*;
import java.io.*;
import java.nio.file.Files;
public class Recipe{
    
    private File recipeFile;
    private Section section;
    private FileWriter writer;
    private String recipeName;
    private LocalTime estimatedTime;
    private ArrayList<Quantity> quantities;
    private Repertoire repertoire;
    private Procedure procedure;

    public Recipe(String recipeName, Repertoire repertoire)
    {
        this.recipeName=recipeName;
        this.repertoire=repertoire;
        this.quantities = new ArrayList<Quantity>();
        this.procedure = new Procedure();
    }
    
    public Recipe(String recipeName, LocalTime estimatedTime, Repertoire repertoire)
    {
        this.recipeName=recipeName;
        this.estimatedTime=estimatedTime;
        this.repertoire=repertoire;
        this.quantities = new ArrayList<Quantity>();
        this.procedure = new Procedure();
        /*recipeFile= new File(this.recipeName+".txt");
        try 
        {
            recipeFile.createNewFile();
            writer=new FileWriter(recipeFile);
            writer.write("\nName: \nEstimated Time: \nIngredients: \nProcedure");
            writer.flush();
        } 
        catch (IOException ioe) 
        {
            System.err.print("Input/Output Stream Error. Please resolve issues and then run the program");
        }*/
    }

    public Recipe(String recipeName, LocalTime estimatedTime, Section thisSection, Repertoire repertoire, File recipeFile)
    {
        this.recipeName=recipeName;
        this.estimatedTime=estimatedTime;
        this.section=section;
        this.repertoire=repertoire;
        this.recipeFile=recipeFile;
        this.quantities = new ArrayList<Quantity>();
        this.procedure = new Procedure();
    }

    public Recipe(String recipeName, Section thisSection, Repertoire repertoire, File recipeFile)
    {
        this.recipeName=recipeName;
        this.repertoire=repertoire;
        this.recipeFile=recipeFile;
        this.quantities = new ArrayList<Quantity>();
        this.procedure = new Procedure();
    }

    public void setTime(LocalTime estimatedTime)
    {
        this.estimatedTime=estimatedTime;
    }

    public void setTime(int hours, int minutes, int seconds)
    {
        this.estimatedTime=LocalTime.of(hours, minutes, seconds);
    }

    public LocalTime getTime()
    {
        return estimatedTime;
    }

    public String getName()
    {
        return recipeName;
    }

    public void getName(String newRecipeName)
    {
        recipeName=newRecipeName;
    }

    public void setToProcedureFromFile(String newStep)
    {
        procedure.addStepFromFile(newStep);
    }

    public String getIngredientString()
    {
        String ingredientString="";
        Iterator<Quantity> ingredientsIter = quantities.iterator();
        while(ingredientsIter.hasNext())
        {
            ingredientString+=(ingredientsIter.next().getIngredientName()+" ");
        }
        return ingredientString;
    }

    public Procedure getProcedure()
    {
        return procedure;
    }

    public void addIngredient(String ingredientName, float quantity, Units unit)
    {
        Ingredient newIngredient = repertoire.doesIngredientExist(ingredientName);
        if(newIngredient==null)
        {
            newIngredient = new Ingredient (ingredientName);
        }
        Quantity newQuantity = new Quantity(newIngredient, quantity, unit);
        quantities.add(newQuantity);
    }

    @Override
    public boolean equals(Recipe anotherRecipe) {
        
        if(this.recipeName.equalsIgnoreCase(anotherRecipe.getName()) && this.getTime().equals(anotherRecipe.getTime()) && /*incomplete*/)

    }

    //public void addIngredient() throws IOException
    /*public void addIngredient(Ingredient newIngredient)
    {
        Scanner input = new Scanner(System.in);
        String newIngredient;
        String inputWords[];
        while(true)
        {
            System.out.print("\nEnter Ingredient : ");
            newIngredient=input.nextLine();
            if(newIngredient.equalsIgnoreCase("done"))
            {
                input.close();
                return;
            }
            inputWords=newIngredient.split(" ");
            Ingredient inputIngredient = new Ingredient(inputWords[0]);
            boolean doesExist=false;;
            Iterator<Quantity> quantityIter = ingredients.iterator();
            while(quantityIter.hasNext())
            {
                if(((Quantity) quantityIter.next()).getIngredientName().equals(inputWords[0]))
                {
                    System.err.print("\nIngredient already exists in this recipe");
                    doesExist=true;
                    break;
                }
            }
            if(doesExist==false)
            {
                Iterator<Ingredient> ingredientsIter = repertoire.getIngredients().iterator();
                if(ingredientsIter.hasNext())
                {
                    Ingredient tempIngredient = ingredientsIter.next();
                    if(tempIngredient.toString().equalsIgnoreCase(inputWords[0]))
                    {
                        Quantity newQuantity = new Quantity(tempIngredient, Float.parseFloat(inputWords[1]), Enum.valueOf(Units.class, inputWords[2].toUpperCase()));
                        ingredients.add(newQuantity);
                        input.close();
                        return;
                    }
                }
            }
        repertoire.addIngredient(inputIngredient, this);
        Quantity newQuantity = new Quantity(inputIngredient, Float.parseFloat(inputWords[1]), Enum.valueOf(Units.class, inputWords[2].toUpperCase()));
        ingredients.add(newQuantity);

        //Scanner fileScanner = new Scanner(recipeFile);
        String file[] = Files.readString(recipeFile.toPath()).split("\n");
        List<String> fileList = Arrays.asList(file);
        //String line, linewords[];
        ArrayList<String> fileContents = new ArrayList<String>();
        fileContents.addAll(fileList);
        Iterator<String> fileContentsIter = fileContents.iterator();
        for(int i=0; fileContentsIter.hasNext(); i++)
        {
            String temp = fileContentsIter.next().toString();
            if(temp.equals("Procedure: "))
            {
                fileContents.add(i, newQuantity.toString()+"\n");
                break;
            }
        }
        Object fileContentsArray[] = (fileContents.toArray());
        String updatedContent = fileContentsArray.toString();
        writer.write(updatedContent);
        }

    }*/

    public void addQuantity(Ingredient ingredient, float quantity, Units unit)
    {
        Quantity newQuantity = new Quantity(ingredient, quantity, unit);
        if(quantityExists(newQuantity))
        quantities.add(newQuantity);
    }

    public boolean quantityExists(Quantity anotherQuantity)
    {
        Iterator<Quantity> quantitiesIter = quantities.iterator();
        while(quantitiesIter.hasNext())
        {
            if(quantitiesIter.next().equals(anotherQuantity))
            {
                return true;
            }
        }
        return false;
    }
    
}
