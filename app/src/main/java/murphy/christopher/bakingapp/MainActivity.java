package murphy.christopher.bakingapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import murphy.christopher.bakingapp.model.Recipe;
import murphy.christopher.bakingapp.utils.NetworkUtils;
import murphy.christopher.bakingapp.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import murphy.christopher.bakingapp.interfaces.GetRecipeDataService;

public class MainActivity extends AppCompatActivity {

    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllRecipes();
    }
    public void getAllRecipes(){
        //Get instance of retrofit
        Retrofit retrofitInstance = RetrofitUtils.getRetrofitInstance();

        //Create the service to get the recipes
        GetRecipeDataService service = retrofitInstance.create(GetRecipeDataService.class);

        //Call the getAllRecipes to get the recipes
        Call<List<Recipe>> recipes = service.getAllRecipes();

        if(NetworkUtils.hasInternetService(this.getApplicationContext())) {
            recipes.enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    recipeList = response.body();
                }

                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    //throw an error
                }
            });
        }else{
            //put some toast in here
        }
    }
}
