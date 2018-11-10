package murphy.christopher.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.List;

import murphy.christopher.bakingapp.fragments.RecipeDetailFragment;
import murphy.christopher.bakingapp.fragments.RecipeIngredientsFragment;
import murphy.christopher.bakingapp.fragments.RecipeInstructionFragment;
import murphy.christopher.bakingapp.model.DataWrapper;
import murphy.christopher.bakingapp.model.Ingredients;
import murphy.christopher.bakingapp.model.Recipe;
import murphy.christopher.bakingapp.model.Steps;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailFragment.RecipeDetailFragmentCallback, RecipeInstructionFragment.PlayerError{

    private Recipe recipeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        if(savedInstanceState != null){
            recipeDetails = Parcels.unwrap(savedInstanceState.getParcelable(Constants.RECIPE_KEY));
        }
        else{
            Intent intent = getIntent();
            recipeDetails = Parcels.unwrap(intent.getParcelableExtra(Constants.RECIPE_KEY));
        }

        //If the device is a tablet, setup the tablet.  Otherwise,
        //treat the device like a phone
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if(isTablet){
            //Set the orientation for a tablet to landscape.
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        FragmentManager mgr = getSupportFragmentManager();

        //These are the fragments that will be loaded in this activity
        //for the tablet.
        RecipeDetailFragment detailFragment = new RecipeDetailFragment();

        //Save the recipe details to use in the fragment
        Bundle arguments = new Bundle();
        arguments.putParcelable(Constants.RECIPE_KEY, Parcels.wrap(recipeDetails));
        detailFragment.setArguments(arguments);

        //Add the fragment for the recipe details to the fragment manager
        mgr.beginTransaction()
                .replace(R.id.recipeDetailFragment, detailFragment)
                .commit();

        sendIngredientsToWidget();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.RECIPE_KEY, Parcels.wrap(recipeDetails));
    }

    @Override
    public void onclick(DataWrapper data) {

        List<Ingredients> ingredients = data.getIngredientsList();
        if((ingredients != null) && !(ingredients.isEmpty())){
            StringBuilder ingredientBuilder = new StringBuilder();

            for(Ingredients singleIngredient : ingredients){
                ingredientBuilder.append(singleIngredient.toString());
            }

            //Setup new ingredient fragment
            RecipeIngredientsFragment rdf = new RecipeIngredientsFragment();

            //Create bundle for the Fragment
            Bundle arguments = new Bundle();
            arguments.putString(Constants.INGREDIENT_LIST, ingredientBuilder.toString());

            rdf.setArguments(arguments);
            attachFragment(rdf);
        }
        else if(data.getSingleStep() != null){
            RecipeInstructionFragment rif = new RecipeInstructionFragment();

            //Create bundle for the Fragment
            Bundle arguments = new Bundle();
            arguments.putParcelable(Constants.SINGLE_STEP, Parcels.wrap(data.getSingleStep()));

            rif.setArguments(arguments);
            attachFragment(rif);
        }
    }
    public void attachFragment(Fragment frag){
        FragmentManager mgr = getSupportFragmentManager();

        //Add the fragment to the fragment manager
        mgr.beginTransaction()
                .replace(R.id.recipeInstructionFragment, frag)
                .commit();
    }

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof RecipeInstructionFragment) {
            RecipeInstructionFragment instruction = (RecipeInstructionFragment) fragment;
            instruction.setErrorNotification(this);
        }
    }

    @Override
    public void onError(){
        Toast.makeText(this,"An error occurred while attempting to play the video.",Toast.LENGTH_LONG).show();
    }

    private void sendIngredientsToWidget(){
        Context context = this;
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        ComponentName thisWidget = new ComponentName(context,RecipeWidget.class);

        StringBuilder sb = new StringBuilder();
        List<Ingredients> ingredients = recipeDetails.getIngredients();
        for(Ingredients ingredient : ingredients){
            sb.append(ingredient.toString());
        }

        remoteViews.setTextViewText(R.id.appwidget_text, sb.toString());
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);
    }
}
