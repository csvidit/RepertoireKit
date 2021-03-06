package initial;

import java.util.*;
import java.io.*;
import java.time.*;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

import org.apache.commons.lang3.StringUtils;

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

    /**
     * 
     * @param bookName
     * @param author
     */
    public Repertoire(String bookName, String author)
    {
        this.author=author;
        this.bookName=bookName;
        this.numRecipes=0;

        sections=MultimapBuilder.treeKeys().arrayListValues().build();
        ingredients=MultimapBuilder.treeKeys().arrayListValues().build();

    }

    /**
     * 
     * @param sectionName
     * @param recipeName
     * @return
     */
    public boolean addRecipe(String sectionName, String recipeName)
    {
        if(++numRecipes>MAX_QUANTITY)
        {
            return false;
        }
        else
        {
            Section currSection = doesSectionExist(sectionName);
            Recipe currRecipe = doesRecipeExist(recipeName);
            if(currSection!=null)
            {
                if(currRecipe==null)
                {
                    Recipe newRecipe = new Recipe(recipeName, this);
                    sections.put(currSection, newRecipe);
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                Section newSection = new Section(sectionName);
                Recipe newRecipe = new Recipe(recipeName, this);
                return true;
            }
        }
    }

    /**
     * 
     * @param recipeName
     * @param thisSection
     * @return
     */
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

    /**
     * 
     * @param thisSection
     * @param thisRecipe
     * @return
     */
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

    /**
     * 
     * @param recipeName
     * @return
     */
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

    /**
     * 
     * @param sectionName
     * @param recipeName
     * @return
     */
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

    /**
     * 
     * @param thisSection
     * @param thisRecipe
     * @return
     */
    public boolean deleteRecipe(Section thisSection, Recipe thisRecipe)
    {
        if(sections.containsEntry(thisSection, thisRecipe))
        {
            sections.remove(thisSection, thisRecipe);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 
     * @param sectionName
     * @return
     */
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

    /**
     * 
     * @param recipeName
     * @return
     */
    public Recipe doesRecipeExist(String recipeName)
    {
        recipeName=recipeName.toUpperCase();
        recipeName=recipeName.trim();
        Iterator<Recipe> recipesIter = sections.values().iterator();
        while(recipesIter.hasNext())
        {
            Recipe tempRecipe = recipesIter.next();
            if(tempRecipe.toString().equals(recipeName))
            {
                return tempRecipe;
            }
        }
        return null;
    }

    /**
     * 
     * @param ingredientName
     * @return
     */
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

    /**
     * 
     * @return
     */
    public Set<Ingredient> getIngredients()
    {
        return ingredients.keySet();
    }

    /**
     * 
     * @param newIngredient
     * @param newRecipe
     */
    public void addIngredient(Ingredient newIngredient, Recipe newRecipe)
    {
        ingredients.put(newIngredient, newRecipe);
    }

    /**
     * 
     * @param recipeName
     * @return
     */
    public Recipe getByName(String recipeName)
    {
        return doesRecipeExist(recipeName);
    }

    /**
     * 
     * @param ingredientName
     * @return 
     */
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

    /**
     * 
     * @param sectionName
     * @return Collection of recipes that match the @param sectionName 
     */
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

    /**
     * 
     * @param thisSection
     * @return Collection of recipes that match the @param thisSection key
     */
    public Collection<Recipe> getBySection(Section thisSection)
    {
        return sections.get(thisSection);
    }

    /**
     * 
     * @param thisTime
     * @return Collection of recipes that require less than or equal to the amount of time as stated by @param thisTime. 
     */
    public Collection<Recipe> getByTime(LocalTime thisTime)
    {
        ArrayList<Recipe> searchResults = new ArrayList<Recipe>();
        Iterator<Recipe> recipesIter=sections.values().iterator();
        while(recipesIter.hasNext())
        {
            Recipe tempRecipe = recipesIter.next();
            if(tempRecipe.getTime().equals(thisTime) || tempRecipe.getTime().isBefore(thisTime))
            {
                searchResults.add(tempRecipe);
            }
        }
        return searchResults;
    }

    /**
     * 
     * @param minutes
     * @return collection of recipes that require less than or equal to the amount of time as stated by @param minutes.
     */
    public Collection<Recipe> getByTime(int minutes)
    {
        ArrayList<Recipe> searchResults = new ArrayList<Recipe>();
        Iterator<Recipe> recipesIter=sections.values().iterator();
        while(recipesIter.hasNext())
        {
            Recipe tempRecipe = recipesIter.next();
            int tempMinutes=(tempRecipe.getTime().getHour()*60)+(tempRecipe.getTime().getMinute())+(tempRecipe.getTime().getSecond()/60);
            if(tempMinutes<=minutes)
            {
                searchResults.add(tempRecipe);
            }
        }
        return searchResults;
    }

    /**
     * 
     * @param query
     * @return collection of recipes that match the search query
     */
    public Collection<Recipe> searchEngine(String query)
    {
        ArrayList<Recipe> searchResults = new ArrayList<Recipe>();
        String queryWords[]=query.split(" ");
        if(queryWords[1].equalsIgnoreCase("time"))
        {
            return getByTime(Integer.parseInt(queryWords[2]));
        }
        else if(queryWords[1].equalsIgnoreCase("recipe"))
        {
            searchResults.add(getByName(queryWords[2]));
            return searchResults;
        }
        else if(queryWords[1].equalsIgnoreCase("section"))
        {
            return getBySection(queryWords[2);
        }
        else if(queryWords[0].equalsIgnoreCase("for"))
        {
            HashSet<Recipe> recipeSet = new HashSet<Recipe>();
            String newQuery=query.substring(11);
            Iterator<Recipe> recipesIter = sections.values().iterator();
            while(recipesIter.hasNext())
            {
                Recipe tempRecipe = recipesIter.next();
                if(tempRecipe.getName().indexOf(newQuery)>-1)
                {
                    recipeSet.add(tempRecipe);
                }
                else if(tempRecipe.getIngredientString().indexOf(newQuery)>-1)
                {
                    recipeSet.add(tempRecipe);
                }
                searchResults.addAll(recipeSet);
            }
        }
        return searchResults;
    }

}