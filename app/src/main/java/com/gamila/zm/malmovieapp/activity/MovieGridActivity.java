package com.gamila.zm.malmovieapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gamila.zm.malmovieapp.AppConstants;
import com.gamila.zm.malmovieapp.R;
import com.gamila.zm.malmovieapp.dummy.DummyContent;
import com.gamila.zm.malmovieapp.fragment.MovieDetailFragment;
import com.gamila.zm.malmovieapp.model.GetMoviesResponse;
import com.gamila.zm.malmovieapp.model.GetMoviesThread;
import com.gamila.zm.malmovieapp.model.MovieResultListener;
import com.gamila.zm.malmovieapp.utils.ImageUtil;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MovieGridActivity extends AppCompatActivity implements MovieResultListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private GetMoviesThread getMoviesThread;
    private ProgressDialog progressDilaog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        View recyclerView = findViewById(R.id.movie_grid);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.movie_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        getMoviesResult(AppConstants.MOVIES_URL_MOST_POPULAR);
    }

    private void getMoviesResult(String moviesUrlMostPopular) {
        if (getMoviesThread == null)
            getMoviesThread = new GetMoviesThread(this);

        getMoviesThread.execute(moviesUrlMostPopular);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new MovieRecyclerViewAdapter(DummyContent.ITEMS));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_movie_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_popularMovies:
                break;
            case R.id.action_topRated:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {

        if (progressDilaog == null) {
            progressDilaog = new ProgressDialog(this);
            progressDilaog.setMessage(getString(R.string.loading));
        }
        progressDilaog.show();
    }

    @Override
    public void hideLoading() {
        if(progressDilaog != null)
            progressDilaog.dismiss();
    }

    @Override
    public void updateByMovieResults(List<GetMoviesResponse.Movie> moviesList) {

    }


    public class MovieRecyclerViewAdapter
            extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public MovieRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_movie_grid, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            ImageUtil.getInstance().loadImageByImageNameInImageView(MovieGridActivity.this, "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg"
                    , holder.mMovieImageView);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(MovieDetailFragment.ARG_MOVIE_ID, holder.mItem.id);
                        MovieDetailFragment fragment = new MovieDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.movie_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra(MovieDetailFragment.ARG_MOVIE_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView mMovieImageView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mMovieImageView = (ImageView) view.findViewById(R.id.row_movieItem_movieImageView);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mItem.details + "'";
            }
        }
    }
}
