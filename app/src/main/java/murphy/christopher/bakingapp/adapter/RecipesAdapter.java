package murphy.christopher.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.model.Recipe;
import murphy.christopher.bakingapp.view_holders.RecipeViewHolder;

@Parcel(Parcel.Serialization.BEAN)
public class RecipesAdapter extends RecyclerView.Adapter<RecipeViewHolder>{

    private List<Recipe> recipes;

    public RecipesAdapter(){
        recipes = new ArrayList<>();
    }

    public RecipesAdapter(List<Recipe> recipeList) {
        this.recipes = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater mInflater = LayoutInflater.from(context);
        View v = mInflater.inflate(R.layout.recipe_card, parent,false);

        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bind(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
