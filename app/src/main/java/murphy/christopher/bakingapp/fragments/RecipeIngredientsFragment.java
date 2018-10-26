package murphy.christopher.bakingapp.fragments;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeIngredientsFragment extends Fragment {

    @BindView(R.id.recipeIngredients)
    TextView ingredientsView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_of_ingredients_fragment, container,false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String ingredientList = "";
        if(savedInstanceState != null){
            ingredientList = savedInstanceState.getString(Constants.INGREDIENT_LIST);
        }
        else {
            Bundle args = getArguments();
            ingredientList = args.getString(Constants.INGREDIENT_LIST);
        }

        ingredientsView.setText(ingredientList);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.INGREDIENT_LIST, ingredientsView.getText().toString());
    }
}
