package murphy.christopher.bakingapp.view_holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import org.parceler.Parcels;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.StepsAndInstructionDetailActivity;
import murphy.christopher.bakingapp.interfaces.RecipeDetailCallback;
import murphy.christopher.bakingapp.model.DataWrapper;
import murphy.christopher.bakingapp.model.IngredientWrapper;
import murphy.christopher.bakingapp.model.Ingredients;
import murphy.christopher.bakingapp.model.Steps;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeDetailViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private List<Ingredients> listOfIngredients;
    private Steps singleStep;
    private RecipeDetailCallback rdListener;
    private View itemView;

    @BindView(R.id.recipeDetailName)
    TextView cardDetailName;

    public RecipeDetailViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public RecipeDetailViewHolder(View itemView, RecipeDetailCallback listener) {
        super(itemView);
        context = itemView.getContext();
        this.itemView = itemView;
        this.rdListener = listener;
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

    @OnClick(R.id.RecipeDetailCard)
    public void onCardClick(){

        //rdListener will not be null when the application is ran
        //on a tablet
        if(rdListener != null){
            //check to see if we have a list of incredients or
            //a set of steps
            if(listOfIngredients != null){
                DataWrapper dw = new DataWrapper();
                dw.setIngredientsList(listOfIngredients);
                rdListener.onRecipeDetailCardClick(dw);
            }
            else if(singleStep != null){
                DataWrapper dw = new DataWrapper();
                dw.setSingleStep(singleStep);
                rdListener.onRecipeDetailCardClick(dw);
            }
        }
        else{
            Intent intent = new Intent(context, StepsAndInstructionDetailActivity.class);

           //Setup a Parcelable wrapper to pass the correct data to the activity
            DataWrapper dw = new DataWrapper();
            if(listOfIngredients != null && !listOfIngredients.isEmpty()){
                dw.setIngredientsList(listOfIngredients);
            }
            else if(singleStep != null) {
                dw.setSingleStep(singleStep);
            }

            intent.putExtra("data", Parcels.wrap(dw));
            context.startActivity(intent);
        }
    }
}
