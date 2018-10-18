package murphy.christopher.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import butterknife.ButterKnife;
import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.model.Recipe;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeDetailFragment extends Fragment {

    Recipe recipeDetails;

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
            recipeDetails = savedInstanceState.getParcelable(Constants.RECIPE_KEY);
        }else{
           Bundle arguments = getArguments();
           recipeDetails = Parcels.unwrap(arguments.getParcelable(Constants.RECIPE_KEY));
        }
    }
}
