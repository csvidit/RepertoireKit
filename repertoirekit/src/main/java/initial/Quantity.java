package initial;

public class Quantity implements Comparable{

    private Ingredient ingredient;
    private float quantity;
    private Units unit;

    public Quantity(Ingredient ingredient)
    {
        this.ingredient=ingredient;
    }

    public Quantity(Ingredient ingredient, float quantity, Units unit)
    {
        this.ingredient=ingredient;
        this.quantity=quantity;
        this.unit=unit;
    }

    public Ingredient getIngredient()
    {
        return ingredient;
    }

    public void setIngredient(Ingredient newIngredient)
    {
        this.ingredient=newIngredient;
    }

    public void setUnit()
    {
        //to implement
    }

    public String getUnitName()
    {
        return unit.toString();
    }

    public String getIngredientName()
    {
        return ingredient.getName();
    }

    @Override
    public String toString()
    {
        return(ingredient.getName()+" - "+Float.toString(quantity)+"\n");
    }

    @Override
    public boolean equals(Object anotherQuantity) {
        anotherQuantity=(Quantity)(anotherQuantity);
        if(this.ingredient.equals(anotherQuantity.getIngredient()));
    }

    @Override
    public int compareTo(Object anotherQuantity) {
        //to implement
    }

}
