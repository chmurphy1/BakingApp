package murphy.christopher.bakingapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.model.Steps;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeInstructionFragment extends Fragment{

    private Steps singleStep;

    @BindView(R.id.StepDescription)
    TextView stepDescription;

    @BindView(R.id.VideoPlayer)
    SimpleExoPlayerView videoPlayerView;

    private SimpleExoPlayer videoPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_instructions_view, container , false);

        ButterKnife.bind(this, view);

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

        stepDescription.setText(singleStep.getDescription());

        initializePlayer(Uri.parse(singleStep.getVideoURL()));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.SINGLE_STEP, Parcels.wrap(singleStep));
    }

    private void initializePlayer(Uri mediaUri) {
        if (videoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            videoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);

            videoPlayerView.setPlayer(videoPlayer);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getContext(), getActivity().getApplication().getApplicationInfo().name);
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
            videoPlayer.prepare(mediaSource);
            videoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        videoPlayer.stop();
        videoPlayer.release();
        videoPlayer = null;
    }
}
