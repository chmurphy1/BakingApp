package murphy.christopher.bakingapp.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.model.IngredientWrapper;
import murphy.christopher.bakingapp.model.Ingredients;
import murphy.christopher.bakingapp.model.Recipe;
import murphy.christopher.bakingapp.model.Steps;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeDetailViewHolder extends RecyclerView.ViewHolder {


    private Context context;
    private List<Ingredients> listOfIngredients;
    private Steps singleStep;

    @BindView(R.id.recipeDetailName)
    TextView cardDetailName;

    public RecipeDetailViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void bind(Object o) {

        if(o instanceof IngredientWrapper){
            listOfIngredients = ((IngredientWrapper) o).getAllIngredients();
            cardDetailName.setText(Constants.INGREDIENT_STRING);
        }
        else if(o instanceof Steps){
            singleStep = (Steps) o;
            cardDetailName.setText(singleStep.getShortDescription());
        }
    }
}
