package initial;

public class Section implements Comparable{
    
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

    public int compareTo(Section x)
    {
        return x.toString().compareTo(this.sectionName);
    }

}
