package initial;

import java.util.*;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.*;

public class Repertoire
{
    public static final int MAX_QUANTITY=Short.MAX_VALUE;
    
    private String bookName;
    private String author;

    private short numRecipes;
    private short numSections;

    private ListMultimap<Section, Recipe> sections;
    private ListMultimap<Ingredient, Recipe> ingredients;

    public Repertoire(String bookName, String author)
    {
        this.author=author;
        this.bookName=bookName;
        this.numRecipes=0;

        sections=MultimapBuilder.treeKeys().arrayListValues().build();
        ingredients=MultimapBuilder.treeKeys().arrayListValues().build();

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
            +"\nIf that section does not exist, it will be created for you."+"\nEnter the section: ");
            String sectionName = input.nextLine();
            sectionName.trim();
            sectionName.toUpperCase();
            Recipe newRecipe = new Recipe(recipeName, this);
            Iterator<Section> sectionsIter = sections.keySet().iterator();
            boolean isAdded=false;
            while(sectionsIter.hasNext())
            {
                Section tempSec = sectionsIter.next();
                if(tempSec.toString().equals(sectionName))
                {
                    sections.put(tempSec, newRecipe);
                    isAdded=true;
                }
            }
            if(isAdded==false)
            {
                Section newSection = new Section(sectionName);
                sections.put(newSection, newRecipe);
            }
            input.close();
            return true;
        }
    }

    public boolean addRecipe(String recipeName, String sectionName)
    {
        if(++numRecipes>MAX_QUANTITY)
        {
            return false;
        }
        else
        {
            sectionName = sectionName.trim();
            sectionName.toUpperCase();
            Recipe newRecipe = new Recipe(recipeName, this);
            Iterator<Section> sectionsIter = sections.keySet().iterator();
            boolean isAdded=false;
            while(sectionsIter.hasNext())
            {
                Section tempSec = sectionsIter.next();
                if(tempSec.toString().equals(sectionName))
                {
                    sections.put(tempSec, newRecipe);
                    isAdded=true;
                }
            }
            if(isAdded==false)
            {
                Section newSection = new Section(sectionName);
                sections.put(newSection, newRecipe);
            }
            input.close();
            return true;
        }
    }

    public boolean addRecipe(String recipeName, Section thisSection)
    {
        if(++numRecipes>MAX_QUANTITY)
        {
            return false;
        }
        else
        {
            Recipe newRecipe = new Recipe(recipeName, this);
            sections.put(thisSection, newRecipe);
            return true;
        }
    }

    public boolean addRecipe(Section thisSection, Recipe thisRecipe)
    {
        if(++numRecipes>MAX_QUANTITY)
        {
            return false;
        }
        else
        {
            sections.put(thisSection, thisRecipe);
            return true;
        }
    }

    public boolean deleteRecipe(String recipeName)
    {
        Iterator<Section> sectionsIter = sections.keySet().iterator();
        while(sectionsIter.hasNext())
        {
            Section tempSection = sectionsIter.next();
            Iterator<Recipe> recipesIter = sections.get(tempSection).iterator();
            while(recipesIter.hasNext())
            {
                Recipe tempRecipe = recipesIter.next();
                if(tempRecipe.getName().equalsIgnoreCase(recipeName))
                {
                    sections.remove(tempSection, tempRecipe);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteRecipe(String sectionName, String recipeName)
    {
        Section currSection=doesSectionExist(sectionName);
        if(currSection!=null)
        {
            Iterator<Recipe> recipesIter = sections.get(currSection).iterator();
            while(recipesIter.hasNext())
            {
                Recipe tempRecipe = recipesIter.next();
                if(tempRecipe.getName().equalsIgnoreCase(recipeName))
                {
                    sections.remove(currSection, tempRecipe);
                    return true;
                }
            }
            return false;
        }
        else
        {
            return false;
        }
    }
    public boolean deleteRecipe(Section thisSection, Recipe thisRecipe)
    {
        if(sections.containsEntry(thisSection, thisRecipe))
        {
            sections.remove(thisSection, thisRecipe);
        }
        else
        {
            return false;
        }
    }

    public Section doesSectionExist(String sectionName)
    {
        sectionName=sectionName.toUpperCase();
        sectionName=sectionName.trim();
        Iterator<Section> sectionsIter = sections.keys().iterator();
        while(sectionsIter.hasNext())
        {
            Section tempSec = sectionsIter.next();
            if(tempSec.toString().equals(sectionName))
            {
                return tempSec;
            }
        }
        return null;
    }

    public Ingredient doesIngredientExist(String ingredientName)
    {
        ingredientName=ingredientName.toUpperCase();
        ingredientName=ingredientName.trim();
        Iterator<Ingredient> ingredientsIter = ingredients.keys().iterator();
        while(ingredientsIter.hasNext())
        {
            Ingredient tempIngredient = ingredientsIter.next();
            if(tempIngredient.toString().equals(ingredientName))
            {
                return tempIngredient;
            }
        }
        return null;
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

    public Collection<Recipe> getByIngredient(String ingredientName)
    {
        Ingredient currIngredient = doesIngredientExist(ingredientName);
        if(currIngredient!=null)
        {
            return ingredients.get(currIngredient);
        }
        else
        {
            return null;
        }

    }

    public Collection<Recipe> getBySection(String sectionName)
    {
        Section currSection=doesSectionExist(sectionName);
        if(currSection!=null)
        {
            return sections.get(currSection);
        }
        else
        {
            return null;
        }
    }

    public Collection<Recipe> getBySection(Section thisSection)
    {
        return sections.get(thisSection);
    }

    
    /*public ArrayList<Recipe> getByIngredient(Ingredient userIngredient)
    {

    }
    public ArrayList<Recipe> getByTime(LocalTime userTime)
    {

    }*/

}