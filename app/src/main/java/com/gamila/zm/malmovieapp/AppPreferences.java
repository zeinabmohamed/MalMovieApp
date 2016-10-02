package com.gamila.zm.malmovieapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.gamila.zm.malmovieapp.model.GetMoviesResponse;
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

    public static void addMovieToFav(Context context, GetMoviesResponse.Movie selectedMovie) {
        List<GetMoviesResponse.Movie> favMoviesIds = loadFavMovies(context);

        favMoviesIds.add(selectedMovie);

        saveSharedPreferencesFavMoviesIdsList(favMoviesIds, context);
    }

    public static void removeMovieFromFav(Context context, GetMoviesResponse.Movie selectedMovie) {
        List<GetMoviesResponse.Movie> favMoviesIds = loadFavMovies(context);
        for (int i = 0; i < favMoviesIds.size(); i++) {
            if (favMoviesIds.get(i).getId() == selectedMovie.getId())
                favMoviesIds.remove(i);
        }
        saveSharedPreferencesFavMoviesIdsList(favMoviesIds, context);
    }

    private static void saveSharedPreferencesFavMoviesIdsList(List<GetMoviesResponse.Movie> favMoviesIds, Context context) {
        SharedPreferences.Editor prefsEditor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(favMoviesIds);
        prefsEditor.putString(FAV_MOVIES_LIST, json);
        prefsEditor.commit();
    }

    public static List<GetMoviesResponse.Movie> loadFavMovies(Context context) {
        List<GetMoviesResponse.Movie> favMoviesIds = new ArrayList<GetMoviesResponse.Movie>();
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(FAV_MOVIES_LIST, "");
        if (json.isEmpty()) {
            favMoviesIds = new ArrayList<GetMoviesResponse.Movie>();
        } else {
            Type type = new TypeToken<List<GetMoviesResponse.Movie>>() {
            }.getType();
            favMoviesIds = gson.fromJson(json, type);
        }
        return favMoviesIds;

    }


    public static boolean isFavMovie(long id, Context context) {
        List<GetMoviesResponse.Movie> favMovies = loadFavMovies(context);
        for (GetMoviesResponse.Movie movie : favMovies) {
            if (movie.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
