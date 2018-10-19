package murphy.christopher.bakingapp.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.view_holders.RecipeDetailViewHolder;
import murphy.christopher.bakingapp.view_holders.RecipeViewHolder;

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailViewHolder> {

    private ArrayList ingredientsAndSteps;

    public RecipeDetailsAdapter(){
        ingredientsAndSteps = new ArrayList();
    }

    public RecipeDetailsAdapter(ArrayList items){
        this.ingredientsAndSteps = items;
    }

    @NonNull
    @Override
    public RecipeDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater mInflater = LayoutInflater.from(context);
        View v = mInflater.inflate(R.layout.recipe_detail_card_layout, parent,false);

        return new RecipeDetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailViewHolder holder, int position) {
        holder.bind(ingredientsAndSteps.get(position));
    }

    @Override
    public int getItemCount() {
        return this.ingredientsAndSteps.size();
    }
}
