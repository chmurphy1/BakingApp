package murphy.christopher.bakingapp.interfaces;

import java.util.List;

import murphy.christopher.bakingapp.model.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

interface GetRecipeDataService {

    //This will get all the recipes for the app to display
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getAllRecipes();
}
