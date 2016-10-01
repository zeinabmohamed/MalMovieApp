package com.gamila.zm.malmovieapp.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamila.zm.malmovieapp.R;
import com.gamila.zm.malmovieapp.model.GetMovieReviewsResponse;
import com.gamila.zm.malmovieapp.model.GetMovieVideosResponse;

/**
 * TODO: document your custom view class.
 */
public class ReviewMovieView extends FrameLayout {
    private GetMovieReviewsResponse.ReviewMovie reviewMovie;


    public ReviewMovieView(Context context, GetMovieReviewsResponse.ReviewMovie reviewMovie) {
        super(context);
        this.reviewMovie =  reviewMovie;
        init(null, 0);
    }

    public ReviewMovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ReviewMovieView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

         inflate(getContext(), R.layout.view_review_movie,this);
        ((TextView) findViewById(R.id.movie_review_contentTextView)).setText(reviewMovie.getContent());
        ((TextView) findViewById(R.id.movie_review_authorTextView)).setText(reviewMovie.getAuthor());

        ((TextView) findViewById(R.id.movie_review_moreTextView)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 10/1/2016  handle on click
            }
        });
    }


}
