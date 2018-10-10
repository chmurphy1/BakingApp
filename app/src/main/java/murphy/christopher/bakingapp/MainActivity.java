package murphy.christopher.bakingapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.bakingapp.adapter.RecipesAdapter;
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
    private GridLayoutManager mLayoutManager;
    private RecipesAdapter rAdapter;

    @BindView(R.id.RecipeCardView)
    RecyclerView mCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setLayoutManager();
        getAllRecipes();
    }
    /*
     *  This method will set the layout manager for a tablet,
     *  a phone in portrait mode and a phone in landscape mode.
     *
     *  I learned this really cool trick by reading tack overflow.
     *  https://stackoverflow.com/questions/9279111/determine-if-the-device-is-a-smartphone-or-tablet
     */
    public void setLayoutManager(){
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        //First test if it is a tablet
        if(isTablet){
            if(isLandscape){
                mLayoutManager = mLayoutManager = new GridLayoutManager(this, 3);
            }
            else{
                mLayoutManager = mLayoutManager = new GridLayoutManager(this, 2);
            }

        }
        else{
            //if it isn't a tablet test to see if it is in landscape mode else
            //it's in portrait mode.
            if(isLandscape){
                mLayoutManager = mLayoutManager = new GridLayoutManager(this, 2);
            }
            else{
                mLayoutManager = mLayoutManager = new GridLayoutManager(this, 1);
            }
        }
    }
    public void setupRecyclerView(){
        mCardView.setLayoutManager(mLayoutManager);
        mCardView.setHasFixedSize(true);
        rAdapter = new RecipesAdapter(recipeList);
        mCardView.setAdapter(rAdapter);
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
                    setupRecyclerView();
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
