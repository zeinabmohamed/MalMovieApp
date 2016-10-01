package com.gamila.zm.malmovieapp.fragment;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamila.zm.malmovieapp.R;
import com.gamila.zm.malmovieapp.activity.MovieDetailActivity;
import com.gamila.zm.malmovieapp.activity.MovieGridActivity;
import com.gamila.zm.malmovieapp.model.GetMoviesResponse;
import com.gamila.zm.malmovieapp.utils.ImageUtil;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieGridActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_MOVIE_ITEM = "movie_item";

    /**
     * The dummy content this fragment is presenting.
     */
    private GetMoviesResponse.Movie mMovieItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_MOVIE_ITEM)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mMovieItem = (GetMoviesResponse.Movie) getArguments().getSerializable(ARG_MOVIE_ITEM);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mMovieItem.getTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mMovieItem != null) {
            ImageUtil.getInstance().loadImageByImageNameInImageView(getActivity(),
                    mMovieItem.getPoster_path(),((ImageView) rootView.findViewById(R.id.movie_ImageView)));
            ((TextView) rootView.findViewById(R.id.movie_realseDateTextView)).setText(mMovieItem.getRelease_date());
            ((TextView) rootView.findViewById(R.id.movie_overViewTextView)).setText(mMovieItem.getOverview());
        }

        return rootView;
    }
}
