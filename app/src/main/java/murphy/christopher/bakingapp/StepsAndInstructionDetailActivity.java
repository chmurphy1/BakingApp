package murphy.christopher.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import java.util.List;

import murphy.christopher.bakingapp.model.DataWrapper;
import murphy.christopher.bakingapp.model.Ingredients;
import murphy.christopher.bakingapp.model.Steps;
import murphy.christopher.bakingapp.utils.Constants;

public class StepsAndInstructionDetailActivity extends AppCompatActivity {

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
}
