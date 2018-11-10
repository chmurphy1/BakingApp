package murphy.christopher.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.adapter.RecipeDetailsAdapter;
import murphy.christopher.bakingapp.interfaces.RecipeDetailCallback;
import murphy.christopher.bakingapp.model.DataWrapper;
import murphy.christopher.bakingapp.model.IngredientWrapper;
import murphy.christopher.bakingapp.model.Ingredients;
import murphy.christopher.bakingapp.model.Recipe;
import murphy.christopher.bakingapp.model.Steps;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeDetailFragment extends Fragment {

    private Recipe recipeDetails;

    @BindView(R.id.recipeRecycler)
    RecyclerView recipeReviewRecylcer;

    private RecipeDetailFragmentCallback callback;
    private LinearLayoutManager layoutMgr;
    private int scrollPos;

    public interface RecipeDetailFragmentCallback {
        public void onclick(DataWrapper data);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View recipeDetailView = inflater.inflate(R.layout.recipe_detail_view,container,false);
        ButterKnife.bind(this, recipeDetailView);

        return recipeDetailView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null ){
            recipeDetails = Parcels.unwrap(savedInstanceState.getParcelable(Constants.RECIPE_KEY));
            scrollPos = savedInstanceState.getInt("Visible Item");
        }else{
           Bundle arguments = getArguments();
           recipeDetails = Parcels.unwrap(arguments.getParcelable(Constants.RECIPE_KEY));
        }

        layoutMgr = new LinearLayoutManager(getContext());
        recipeReviewRecylcer.setLayoutManager(layoutMgr);
        recipeReviewRecylcer.setHasFixedSize(true);
        recipeReviewRecylcer.setAdapter(setupRecipeDetailAdapter());
        layoutMgr.scrollToPosition(scrollPos);
    }

    //This function places the data for the ingredients and steps
    //into an array list and creates a new adapter for that data.
    public RecipeDetailsAdapter setupRecipeDetailAdapter(){
        ArrayList ingredientsAndSteps = new ArrayList();

        List<Ingredients> ingredients = recipeDetails.getIngredients();
        if(ingredients != null){
            IngredientWrapper wrapper = new IngredientWrapper();
            wrapper.addAllIngredients(ingredients);
            ingredientsAndSteps.add(wrapper);
        }

        List<Steps> listOfSteps = recipeDetails.getSteps();
        for(Steps s : listOfSteps){
            ingredientsAndSteps.add(s);
        }


        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if(isTablet){
            RecipeDetailCallback listener = new RecipeDetailCallback(){

                @Override
                public void onRecipeDetailCardClick(DataWrapper data) {
                    callback.onclick(data);
                }
            };
            return new RecipeDetailsAdapter(ingredientsAndSteps, listener);
        }
        else {
            return new RecipeDetailsAdapter(ingredientsAndSteps);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.RECIPE_KEY, Parcels.wrap(recipeDetails));
        outState.putInt("Visible Item",layoutMgr.findFirstCompletelyVisibleItemPosition());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (RecipeDetailFragmentCallback) getActivity();
    }
}
