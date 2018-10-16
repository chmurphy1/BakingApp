package murphy.christopher.bakingapp.view_holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.RecipeDetailsActivity;
import murphy.christopher.bakingapp.model.Recipe;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private Recipe recipe;

    @BindView(R.id.recipeName)
    TextView cardName;

    public RecipeViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }
    public void bind(Recipe recipe){
        this.recipe = recipe;
        cardName.setText(recipe.getName());
    }
    @OnClick(R.id.RecipeCard)
    public void onClickCard(){
        Intent recipeIntent = new Intent(context, RecipeDetailsActivity.class);
        recipeIntent.putExtra(Constants.RECIPE_KEY, Parcels.wrap(recipe));
        context.startActivity(recipeIntent);
    }
}
