package initial;

public class Section {
    
    public String sectionName;
    public short numRecipes;

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
        return sectionName;
    }

}
