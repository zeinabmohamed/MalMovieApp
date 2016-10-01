package com.gamila.zm.malmovieapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zeinab Mohamed on 10/1/2016.
 */

public class GetMoviesResponse {

    /**
     * page : 1
     * results : [{"poster_path":"/z6BP8yLwck8mN9dtdYKkZ4XGa3D.jpg","adult":false,"overview":"A big screen remake of John Sturges' classic western The Magnificent Seven, itself a remake of Akira Kurosawa's Seven Samurai. Seven gun men in the old west gradually come together to help a poor village against savage thieves.","release_date":"2016-09-14","genre_ids":[28,12,37],"id":333484,"original_title":"The Magnificent Seven","original_language":"en","title":"The Magnificent Seven","backdrop_path":"/g54J9MnNLe7WJYVIvdWTeTIygAH.jpg","popularity":30.872434,"vote_count":226,"video":false,"vote_average":4.56}]
     * total_results : 19587
     * total_pages : 980
     */

    private int page;
    private int total_results;
    private int total_pages;
    /**
     * poster_path : /z6BP8yLwck8mN9dtdYKkZ4XGa3D.jpg
     * adult : false
     * overview : A big screen remake of John Sturges' classic western The Magnificent Seven, itself a remake of Akira Kurosawa's Seven Samurai. Seven gun men in the old west gradually come together to help a poor village against savage thieves.
     * release_date : 2016-09-14
     * genre_ids : [28,12,37]
     * id : 333484
     * original_title : The Magnificent Seven
     * original_language : en
     * title : The Magnificent Seven
     * backdrop_path : /g54J9MnNLe7WJYVIvdWTeTIygAH.jpg
     * popularity : 30.872434
     * vote_count : 226
     * video : false
     * vote_average : 4.56
     */

    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public static class Movie implements Serializable {
        private String poster_path;
        private boolean adult;
        private String overview;
        private String release_date;
        private long id;
        private String original_title;
        private String original_language;
        private String title;
        private String backdrop_path;
        private double popularity;
        private int vote_count;
        private boolean video;
        private double vote_average;
        private List<Integer> genre_ids;

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }
    }
}
