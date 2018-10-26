package murphy.christopher.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.model.Steps;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeInstructionFragment extends Fragment {

    private Steps singleStep;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_instructions_view, container , false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null){
            singleStep = Parcels.unwrap(savedInstanceState.getParcelable(Constants.SINGLE_STEP));
        }
        else {
            Bundle args = getArguments();
            singleStep = Parcels.unwrap(args.getParcelable(Constants.SINGLE_STEP));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.SINGLE_STEP, Parcels.wrap(singleStep));
    }
}
