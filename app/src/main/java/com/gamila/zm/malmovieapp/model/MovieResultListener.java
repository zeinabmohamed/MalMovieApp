package com.gamila.zm.malmovieapp.model;

import java.util.List;

/**
 * Created by Zeinab Mohamed on 10/1/2016.
 */
public interface MovieResultListener {

    void showLoading();

    void hideLoading();

    void updateByMovieResults(List<GetMoviesResponse.Movie> moviesList);
}
