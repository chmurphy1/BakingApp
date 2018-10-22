package murphy.christopher.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import java.util.List;

import murphy.christopher.bakingapp.model.DataWrapper;
import murphy.christopher.bakingapp.model.Ingredients;
import murphy.christopher.bakingapp.model.Steps;

public class StepsAndInstructionDetailActivity extends AppCompatActivity {

    private Steps singleStep;
    private List<Ingredients> ingredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_and_instruction_detail);

        setupData();

    }

    private void setupData(){
        Intent intent = getIntent();
        DataWrapper dw = Parcels.unwrap(intent.getParcelableExtra("data"));

        ingredientsList = dw.getIngredientsList();
        singleStep = dw.getSingleStep();
    }
}
