package murphy.christopher.bakingapp.view_holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.model.Recipe;

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
}
