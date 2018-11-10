package murphy.christopher.bakingapp.fragments;


import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import org.parceler.Parcels;
import butterknife.BindView;
import butterknife.ButterKnife;
import murphy.christopher.bakingapp.R;
import murphy.christopher.bakingapp.model.Steps;
import murphy.christopher.bakingapp.utils.Constants;

public class RecipeInstructionFragment extends Fragment implements Player.EventListener {

    private Steps singleStep;

    @BindView(R.id.StepDescription)
    TextView stepDescription;

    @BindView(R.id.VideoPlayer)
    PlayerView videoPlayerView;

    private SimpleExoPlayer videoPlayer;
    private long playerPos = C.TIME_UNSET;
    private Uri videoUri;

    public interface PlayerError{
        void onError();
    }

    PlayerError errorNotification;

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
            playerPos = savedInstanceState.getLong("CURRENT_POSITION");
        }
        else {
            Bundle args = getArguments();
            singleStep = Parcels.unwrap(args.getParcelable(Constants.SINGLE_STEP));
        }

        stepDescription.setText(singleStep.getDescription());
        videoUri = Uri.parse(singleStep.getVideoURL());
        initializePlayer(videoUri);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.SINGLE_STEP, Parcels.wrap(singleStep));
        outState.putLong("CURRENT_POSITION", playerPos);
    }

    private void initializePlayer(Uri mediaUri) {
        if (videoPlayer == null) {
            Handler handle = new Handler();

            TrackSelector trackSelector = new DefaultTrackSelector();
            videoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

            videoPlayerView.setPlayer(videoPlayer);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getContext(), getActivity().getApplication().getApplicationInfo().name);
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(), userAgent);

            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaUri);

            videoPlayer.addListener(this);
            videoPlayer.prepare(videoSource);

            if (playerPos != C.TIME_UNSET) {
                videoPlayer.seekTo(playerPos);
            }
            videoPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(videoPlayer != null) {
            videoPlayer.stop();
            videoPlayer.release();
            videoPlayer = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (videoPlayer != null) {
            playerPos = videoPlayer.getCurrentPosition();
            videoPlayer.stop();
            videoPlayer.release();
            videoPlayer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (videoUri != null)
            initializePlayer(videoUri);
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        errorNotification.onError();
        videoPlayer.stop();
        videoPlayer.release();
        error.printStackTrace();
    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }
    public void setErrorNotification(PlayerError errorNotification) {
        this.errorNotification = errorNotification;
    }
}
