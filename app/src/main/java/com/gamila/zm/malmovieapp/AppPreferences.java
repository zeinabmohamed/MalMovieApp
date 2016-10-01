package com.gamila.zm.malmovieapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeinab Mohamed on 10/2/2016.
 */

public final class AppPreferences {

    private static final String MAL_MOVIE_APP_SHARED_PREFRENCES_KEY = "mal_movie_app_shared_preferences";
    private static final String FAV_MOVIES_LIST = "fav_movies";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MAL_MOVIE_APP_SHARED_PREFRENCES_KEY, Context.MODE_PRIVATE);
    }

    public static void addMovieToFav(Context context, long id) {
        List<Long> favMoviesIds = loadFavMovies(context);

        favMoviesIds.add(id);

        saveSharedPreferencesFavMoviesIdsList(favMoviesIds, context);
    }

    public static void removeMovieFromFav(Context context, long id) {
        List<Long> favMoviesIds = loadFavMovies(context);
        favMoviesIds.remove(id);
        saveSharedPreferencesFavMoviesIdsList(favMoviesIds, context);
    }

    private static void saveSharedPreferencesFavMoviesIdsList(List<Long> favMoviesIds, Context context) {
        SharedPreferences.Editor prefsEditor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(favMoviesIds);
        prefsEditor.putString(FAV_MOVIES_LIST, json);
        prefsEditor.commit();
    }

    private static List<Long> loadFavMovies(Context context) {
        List<Long> favMoviesIds = new ArrayList<Long>();
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(FAV_MOVIES_LIST, "");
        if (json.isEmpty()) {
            favMoviesIds = new ArrayList<Long>();
        } else {
            Type type = new TypeToken<List<Long>>() {
            }.getType();
            favMoviesIds = gson.fromJson(json, type);
        }
        return favMoviesIds;

    }


    public static boolean isFavMovie(long id, Context context) {
       List<Long> idsFav = loadFavMovies(context);
        if(idsFav.contains(id)){
            return true;
        }else {
            return false;
        }
    }
}
