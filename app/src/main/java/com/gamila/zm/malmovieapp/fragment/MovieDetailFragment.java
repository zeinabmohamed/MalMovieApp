package com.gamila.zm.malmovieapp.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gamila.zm.malmovieapp.AppPreferences;
import com.gamila.zm.malmovieapp.R;
import com.gamila.zm.malmovieapp.activity.MovieDetailActivity;
import com.gamila.zm.malmovieapp.activity.MovieGridActivity;
import com.gamila.zm.malmovieapp.custom_view.ReviewMovieView;
import com.gamila.zm.malmovieapp.custom_view.TrailerVideoView;
import com.gamila.zm.malmovieapp.model.GetMovieReviewsApiThread;
import com.gamila.zm.malmovieapp.model.GetMovieReviewsResponse;
import com.gamila.zm.malmovieapp.model.GetMovieVideosApiThread;
import com.gamila.zm.malmovieapp.model.GetMovieVideosResponse;
import com.gamila.zm.malmovieapp.model.GetMoviesResponse;
import com.gamila.zm.malmovieapp.utils.ImageUtil;

import java.util.ArrayList;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieGridActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment implements GetMovieVideosApiThread.MovieVideosResultListener, GetMovieReviewsApiThread.MovieReviewsResultListener {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_MOVIE_ITEM = "movie_item";
    private static final String TAG = MovieDetailFragment.class.getName();
    private static final String TRALIERS_LIST = "trilers_list";
    private static final String REVIEWS_LIST = "reviews_list";

    /**
     * The {@link com.gamila.zm.malmovieapp.model.GetMoviesResponse.Movie} content this fragment is presenting.
     */
    private GetMoviesResponse.Movie mMovieItem;
    private ProgressDialog progressDialog;
    private LinearLayout trailersContainer;
    private LinearLayout reviewsContainer;
    private ArrayList<GetMovieVideosResponse.VideoInfo> videosList;
    private ArrayList<GetMovieReviewsResponse.ReviewMovie> reviewsList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TRALIERS_LIST,videosList);
        outState.putSerializable(REVIEWS_LIST, reviewsList);
        outState.putSerializable(ARG_MOVIE_ITEM,mMovieItem);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){

            mMovieItem = (GetMoviesResponse.Movie) savedInstanceState.getSerializable(ARG_MOVIE_ITEM);
            videosList = (ArrayList<GetMovieVideosResponse.VideoInfo>) savedInstanceState.getSerializable(TRALIERS_LIST);
            reviewsList = (ArrayList<GetMovieReviewsResponse.ReviewMovie>) savedInstanceState.getSerializable(REVIEWS_LIST);

        }else {
            if (getArguments().containsKey(ARG_MOVIE_ITEM)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                mMovieItem = (GetMoviesResponse.Movie) getArguments().getSerializable(ARG_MOVIE_ITEM);
                Activity activity = this.getActivity();
                CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
                if (appBarLayout != null) {
                    appBarLayout.setTitle(getString(R.string.movie_details));
                }


                getMovieVideos(mMovieItem.getId());
                getMovieReviews(mMovieItem.getId());
            }
        }
    }
    private void getMovieVideos(long id) {
        GetMovieVideosApiThread getMovieVideosApiThread = new GetMovieVideosApiThread(getActivity(),this);
        getMovieVideosApiThread.execute(id);
    }
    private void getMovieReviews(long id) {
        GetMovieReviewsApiThread getMovieVideosApiThread = new GetMovieReviewsApiThread(getActivity(),this);
        getMovieVideosApiThread.execute(id);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.movie_detail, container, false);
        // Show the videos content as text in a TextView.
        if (mMovieItem != null) {
            ImageUtil.getInstance().loadImageByImageNameInImageView(getActivity(),
                    mMovieItem.getPoster_path(),((ImageView) rootView.findViewById(R.id.movie_ImageView)));
            ((TextView) rootView.findViewById(R.id.movie_titleTextView)).setText(mMovieItem.getTitle());
            ((TextView) rootView.findViewById(R.id.movie_realseDateTextView)).setText(mMovieItem.getRelease_date());
            ((TextView) rootView.findViewById(R.id.movie_rateTextView)).setText(mMovieItem.getVote_average()+"/10");
            ((TextView) rootView.findViewById(R.id.movie_overViewTextView)).setText(mMovieItem.getOverview());

            if(AppPreferences.isFavMovie(mMovieItem.getId(), getActivity())){
                ((Button) rootView.findViewById(R.id.movie_addToFavButton)).
                        setBackgroundResource(R.color.colorAccent);
            }

            ((Button) rootView.findViewById(R.id.movie_addToFavButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(AppPreferences.isFavMovie(mMovieItem.getId(), getActivity())){
                        AppPreferences.removeMovieFromFav(getActivity(),mMovieItem.getId());
                        ((Button) rootView.findViewById(R.id.movie_addToFavButton)).
                                setBackgroundResource(android.R.color.darker_gray);
                    }else{
                        AppPreferences.addMovieToFav(getActivity(),mMovieItem.getId());
                        ((Button) rootView.findViewById(R.id.movie_addToFavButton)).
                                setBackgroundResource(R.color.colorAccent);
                    }

                }
            });

            trailersContainer = ((LinearLayout) rootView.findViewById(R.id.movie_trailersContainer));
            if(videosList != null  && videosList.size()>0){
                for (GetMovieVideosResponse.VideoInfo videoInfo:
                     videosList) {
                    trailersContainer.addView(new TrailerVideoView(getActivity(),videoInfo));
                }
            }
            reviewsContainer = ((LinearLayout) rootView.findViewById(R.id.movie_reviewsContainer));
            if(reviewsList != null  && reviewsList.size()>0){
                for (GetMovieReviewsResponse.ReviewMovie reviewMovie:
                        reviewsList) {
                    reviewsContainer.addView(new ReviewMovieView(getActivity(),reviewMovie));
                }
            }
        }
        return rootView;
    }
    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCancelable(false);
            progressDialog.setMessage(getString(R.string.loading));
        }
        progressDialog.show();
    }
    @Override
    public void hideLoading() {
        if(progressDialog != null)
            progressDialog.dismiss();
    }
    @Override
    public void updateByMovieVideosResults(ArrayList<GetMovieVideosResponse.VideoInfo> movieVideosList) {
        Log.i(TAG, "updateByMovieVideosResults: ");
        videosList =movieVideosList;
        for (GetMovieVideosResponse.VideoInfo videoInfo :
                movieVideosList) {
            trailersContainer.addView(new TrailerVideoView(getActivity(),videoInfo));
        }
    }
    @Override
    public void showError(Exception result) {
        Toast.makeText(getActivity(), result.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void updateByMovieReviewsResults(ArrayList<GetMovieReviewsResponse.ReviewMovie> results) {
        Log.i(TAG, "updateByMovieVideosResults: ");
        reviewsList = results;
        for (GetMovieReviewsResponse.ReviewMovie reviewMovie : results) {
            reviewsContainer.addView(new ReviewMovieView(getActivity(),reviewMovie));
        }
    }
}
