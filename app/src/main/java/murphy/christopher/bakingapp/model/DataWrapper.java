package murphy.christopher.bakingapp.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

@Parcel(Parcel.Serialization.BEAN)
public class DataWrapper {

    private Steps singleStep;
    private List<Ingredients> ingredientsList;

    public DataWrapper(){

    }

    @ParcelConstructor
    public DataWrapper(Steps singleStep, List<Ingredients> ingredientsList){
        this.singleStep = singleStep;
        this.ingredientsList = ingredientsList;
    }

    public Steps getSingleStep() {
        return singleStep;
    }

    public void setSingleStep(Steps singleStep) {
        this.singleStep = singleStep;
    }

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
