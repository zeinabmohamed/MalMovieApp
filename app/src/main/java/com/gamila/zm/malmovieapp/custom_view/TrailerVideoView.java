package com.gamila.zm.malmovieapp.custom_view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamila.zm.malmovieapp.R;
import com.gamila.zm.malmovieapp.model.GetMovieVideosResponse;

/**
 * TODO: document your custom view class.
 */
public class TrailerVideoView extends FrameLayout {
    private GetMovieVideosResponse.VideoInfo videoInfo;


    public TrailerVideoView(Context context, GetMovieVideosResponse.VideoInfo videoInfo) {
        super(context);
        this.videoInfo =  videoInfo;
        init(null, 0);
    }

    public TrailerVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TrailerVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

         inflate(getContext(), R.layout.view_trailer,this);
        ((TextView) findViewById(R.id.movie_trailer_titleTextView)).setText(videoInfo.getName());
        ((ImageView) findViewById(R.id.movie_trailer_playIconImageView)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + videoInfo.getKey()));
                TrailerVideoView.this.getContext().startActivity(intent);
            }
        });
    }


}
