package murphy.christopher.bakingapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//This is a simple wrapper for a list of ingredients
public class IngredientWrapper {

    private ArrayList<Ingredients> ingredientList;

    public IngredientWrapper(){
        ingredientList = new ArrayList<>();
    }

    public boolean addIngredients(Ingredients ingredient){
        return ingredientList.add(ingredient);
    }

    public boolean addAllIngredients(Collection<? extends Ingredients> ingredients){
        return ingredientList.addAll(ingredients);
    }

    public Ingredients getIngredient(int position){
        return ingredientList.get(position);
    }

    public List<Ingredients> getAllIngredients(){
        return ingredientList;
    }
}
