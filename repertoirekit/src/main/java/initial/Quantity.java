package initial;

public class Quantity {

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

    public String getIngredientName()
    {
        return ingredient.getName();
    }

    @Override
    public String toString()
    {
        return(ingredient.getName()+" - "+Float.toString(quantity));
    }

}
