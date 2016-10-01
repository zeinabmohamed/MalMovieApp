package com.gamila.zm.malmovieapp;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Zeinab Mohamed on 9/30/2016.
 */

public class AppConstants {

    public static String MOVIES_URL_MOST_POPULAR = "http://api.themoviedb.org/3/movie/popular?api_key"
            +"="+BuildConfig.THE_MOVIE_DB_API_KEY;
    public static String MOVIES_URL_TOP_RATED = "http://api.themoviedb.org/3/movie/top_rated?api_key"
            +"="+BuildConfig.THE_MOVIE_DB_API_KEY;

    @StringDef({MOVIE_LIST_TYPE.POPULAR, MOVIE_LIST_TYPE.TOP_RATED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MOVIE_LIST_TYPE {

        String POPULAR = "popular";
        String TOP_RATED = "top_rated";
    }

    public static String MOVIE_VIDEOS_URL = "http://api.themoviedb.org/3/movie/%d/videos?api_key"
            +"="+BuildConfig.THE_MOVIE_DB_API_KEY;

    public static String MOVIE_REVIEWS_URL = "http://api.themoviedb.org/3/movie/%d/reviews?api_key"
            +"="+BuildConfig.THE_MOVIE_DB_API_KEY;

    public static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    @StringDef({IMAGE_SIZE.W_92,
            IMAGE_SIZE.W_154, IMAGE_SIZE.W_185, IMAGE_SIZE.W_342, IMAGE_SIZE.W_500, IMAGE_SIZE.W_780})
    @Retention(RetentionPolicy.SOURCE)
    public @interface IMAGE_SIZE {

        String W_92 = "w92";
        String W_154 = "w154";
        String W_185 = "w185";
        String W_342 = "w342";
        String W_500 = "w500";
        String W_780 = "w780";
    }



}
