package initial;

import java.util.Collection;
import java.util.Iterator;

public class Section implements Comparable{
    
    private String sectionName;
    private short numRecipes;
    private Repertoire repertoire;

    public Section(String sectionName)
    {
        this.sectionName=sectionName;
    }

    public short getNumRecipes() {
        return numRecipes;
    }

    @Override
    public String toString()
    {
        Collection<Recipe> recipeCollection = repertoire.getBySection(this);
        String toString="";
        Iterator<Recipe> recipesIter = recipeCollection.iterator();
        for(int i=1; recipesIter.hasNext(); i++)
        {
            toString+=i+". "+recipesIter.next().getName()+"\n";
        }
        return toString;
    }

    public int compareTo(Object x)
    {
        return ((Section)x).toString().compareTo(this.sectionName);
    }

}
