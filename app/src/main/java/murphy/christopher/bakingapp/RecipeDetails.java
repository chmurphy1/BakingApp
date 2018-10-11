package murphy.christopher.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import murphy.christopher.bakingapp.model.Recipe;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        Intent intent = getIntent();
        Recipe recipe = Parcels.unwrap(intent.getParcelableExtra(Constants.RECIPE_KEY));
    }
}
