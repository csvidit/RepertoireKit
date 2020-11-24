package initial;

import java.util.*;
public class Repertoire
{
    public static final int MAX_QUANTITY=Short.MAX_VALUE;
    
    private String bookName;
    private String author;

    private short numRecipes;
    private short numSections;

    private LinkedHashMap<Section, ArrayList<Recipe>> sections;
    private HashMap<Ingredient, ArrayList<Recipe>> ingredients;

    public Repertoire(String bookName, String author)
    {
        this.author=author;
        this.bookName=bookName;
        this.numRecipes=0;
        sections = new LinkedHashMap<Section, ArrayList<Recipe>>();
        ingredients = new  HashMap<Ingredient, ArrayList<Recipe>>();

    }

    public boolean addRecipe(String recipeName)
    {
        if(++numRecipes>MAX_QUANTITY)
        {
            return false;
        }
        else
        {
            //recipes.add(newRecipe);
            Scanner input = new Scanner(System.in);
            System.out.print("\nEnter the section where you would like to insert this recipe."
            +"\nIf that section does not exist, it will be created for you."+
            "\nSections that currently exist:");
            Iterator<Section> sectionsIter = sections.keySet().iterator();
            while(sectionsIter.hasNext())
            {
                System.out.print("\n"+sectionsIter.next().toString());
            }
            System.out.print("\nEnter the section: ");
            String sectionName = input.nextLine();
            sectionName.trim();
            sectionName.toUpperCase();
            Section newSection = new Section(sectionName);
            Recipe newRecipe = new Recipe(recipeName, this);
            if(sections.containsKey(newSection))
            {
                ArrayList<Recipe> currList = sections.get(newSection);
                currList.add(newRecipe);
            }
            else
            {
                ArrayList<Recipe> newList = new ArrayList<Recipe>();
                newList.add(newRecipe);
                sections.put(newSection, newList);
            }
            return true;
        }
    }

    public boolean deleteRecipe(String recipeName)
    {

    }

    public Set<Ingredient> getIngredients()
    {
        return ingredients.keySet();
    }

    public HashMap<Ingredient, ArrayList<Recipe>> getIngredientMap()
    {
        return ingredients;
    }

    public void addIngredient(Ingredient newIngredient, Recipe newRecipe)
    {
        Collection newCollection = 
        ingredients.put(newIngredient, newRecipe);
    }

    public List<Recipe> getByIngredient(String ingredientName)
    {
        Iterator<Ingredient> ingredientsIter = getIngredients().iterator();
        Ingredient temp;
        List<Recipe> requestedList = new List<Recipe>;
        while(ingredientsIter.hasNext())
        {
            temp=ingredientsIter.next();
            if(temp.toString().equalsIgnoreCase(ingredientName))
            {
                requestedList.add(ingredients.get(temp))
            }
        }
    }

    /*public ArrayList<Recipe> getByIngredient(Ingredient userIngredient)
    {

    }
    public ArrayList<Recipe> getByTime(LocalTime userTime)
    {

    }*/

}