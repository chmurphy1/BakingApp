package murphy.christopher.bakingapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import butterknife.BindView;
import murphy.christopher.bakingapp.fragments.RecipeDetailFragment;
import murphy.christopher.bakingapp.fragments.RecipeInstructionFragment;
import murphy.christopher.bakingapp.model.Recipe;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeDetailsActivity extends AppCompatActivity {

    private Recipe recipeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Intent intent = getIntent();
        recipeDetails = Parcels.unwrap(intent.getParcelableExtra(Constants.RECIPE_KEY));

        //If the device is a tablet, setup the tablet.  Otherwise,
        //treat the device like a phone
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if(isTablet){
            setupTablet();
        }
        else{
            setupPhone();
        }

    }
    //This method will setup the layout and fragments for a tablet
    public void setupTablet(){
        //Set the orientation for a tablet to landscape.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        FragmentManager mgr = getSupportFragmentManager();

        //These are the fragments that will be loaded in this activity
        //for the tablet.
        RecipeDetailFragment detailFragment = new RecipeDetailFragment();
        RecipeInstructionFragment instructionFragment = new RecipeInstructionFragment();

        //Add the fragment for the recipe details to the fragment manager
        mgr.beginTransaction()
                .add(R.id.recipeDetailFragment, detailFragment)
                .commit();

        //Add the fragment for the recipe instructions to the fragment manager
        /*
        mgr.beginTransaction()
                .add(R.id.recipeDetailInstructions, instructionFragment)
                .commit();
                */
    }
    public void setupPhone(){
        FragmentManager mgr = getSupportFragmentManager();

        //Only the recipe detail Fragment will be loaded since the phone's
        //screen is only large enough to handle one fragment.
        RecipeDetailFragment detailFragment = new RecipeDetailFragment();

        //Add the fragment for the recipe details to the fragment manager
        mgr.beginTransaction()
                .add(R.id.recipeDetailFragment, detailFragment)
                .commit();
    }
}
