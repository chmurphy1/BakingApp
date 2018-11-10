package murphy.christopher.bakingapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.List;

import murphy.christopher.bakingapp.fragments.RecipeIngredientsFragment;
import murphy.christopher.bakingapp.fragments.RecipeInstructionFragment;
import murphy.christopher.bakingapp.model.DataWrapper;
import murphy.christopher.bakingapp.model.Ingredients;
import murphy.christopher.bakingapp.model.Steps;
import murphy.christopher.bakingapp.utils.Constants;

public class StepsAndInstructionDetailActivity extends AppCompatActivity implements RecipeInstructionFragment.PlayerError{

    private Steps singleStep;
    private List<Ingredients> ingredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_and_instruction_detail);

        if(savedInstanceState == null){
            setupData();
        }
        else if(savedInstanceState != null){
            singleStep = Parcels.unwrap(savedInstanceState.getParcelable(Constants.SINGLE_STEP));
            ingredientsList = Parcels.unwrap(savedInstanceState.getParcelable(Constants.INGREDIENT_LIST));
        }

        if((ingredientsList != null) && !(ingredientsList.isEmpty())){
            StringBuilder ingredientBuilder = new StringBuilder();

            for (Ingredients singleIngredient : ingredientsList) {
                ingredientBuilder.append(singleIngredient.toString());
            }

            //Setup new ingredient fragment
            RecipeIngredientsFragment rdf = new RecipeIngredientsFragment();

            //Create bundle for the Fragment
            Bundle arguments = new Bundle();
            arguments.putString(Constants.INGREDIENT_LIST, ingredientBuilder.toString());

            rdf.setArguments(arguments);
            attachFragment(rdf);
        }else if(singleStep != null){
            RecipeInstructionFragment rif = new RecipeInstructionFragment();

            //Create a buntdle to pass arguements to fragment
            Bundle arguments = new Bundle();
            arguments.putParcelable(Constants.SINGLE_STEP, Parcels.wrap(singleStep));

            //Sends the fragment parameters
            rif.setArguments(arguments);

            //attaches the fragment to the activity
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

    private void setupData(){
        Intent intent = getIntent();
        DataWrapper dw = Parcels.unwrap(intent.getParcelableExtra("data"));

        ingredientsList = dw.getIngredientsList();
        singleStep = dw.getSingleStep();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the single step it's not nul
        if(singleStep != null){
            outState.putParcelable(Constants.SINGLE_STEP, Parcels.wrap(singleStep));
        }

        //Save the ingredient list if it is not null and it's not empty
        if((ingredientsList != null) && !(ingredientsList.isEmpty())){
            outState.putParcelable(Constants.INGREDIENT_LIST, Parcels.wrap(ingredientsList));
        }
    }
    public void onError(){
        Toast.makeText(this,"An error occurred while attempting to play the video.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);

        if (fragment instanceof RecipeInstructionFragment) {
            RecipeInstructionFragment instruction = (RecipeInstructionFragment) fragment;
            instruction.setErrorNotification(this);
        }
    }
}
