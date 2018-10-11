package murphy.christopher.bakingapp.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.ArrayList;
import java.util.List;

@Parcel(Parcel.Serialization.BEAN)
public class Recipe {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("ingredients")
    private List<Ingredients> ingredients;

    @SerializedName("steps")
    private List<Steps> steps;

    @SerializedName("servings")
    private int servings;

    @SerializedName("image")
    private String image;

    public Recipe(){
        this.id = 0;
        this.name = "";
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();
        this.servings = 0;
        this.image = "";
    }

    @ParcelConstructor
    public Recipe(int id, String name, List<Ingredients> ingredients,
                  List<Steps> steps, int servings, String image){
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredientsList(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
