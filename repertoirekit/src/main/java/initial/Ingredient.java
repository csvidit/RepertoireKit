package initial;

public class Ingredient implements Comparable{
    
    private String name;

    public Ingredient(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name+"\n";
    }

    public int compareTo(Ingredient x)
    {
        return x.toString().compareTo(this.name);
    }

}
