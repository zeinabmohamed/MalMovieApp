package com.gamila.zm.malmovieapp.model;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zeinab Mohamed on 10/1/2016.
 */

public class GetMoviesThread extends AsyncTask<String, Object, GetMoviesResponse> {

    private static final String TAG = GetMoviesThread.class.getSimpleName();
    private MovieResultListener movieResultListener;

    public GetMoviesThread(MovieResultListener movieResultListener) {
        this.movieResultListener = movieResultListener;
    }


    @Override
    protected void onPreExecute() {
        movieResultListener.showLoading();
    }

    protected GetMoviesResponse doInBackground(String... params) {
        Log.d(TAG, "doInBackground() called with: params " + params[0]);
        // handle request
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String moviesListJsonStr = null;

        try {
            URL url = new URL(params[0]);
            // Create the request to themoviedb, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            moviesListJsonStr = buffer.toString();
            Gson gson = new Gson();
            GetMoviesResponse response = gson.fromJson(moviesListJsonStr, GetMoviesResponse.class);
            return response;
        } catch (IOException e) {
            Log.e(TAG, "Error ", e);
            // If the code didn't successfully .
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }

    }

    @Override
    protected void onPostExecute(GetMoviesResponse getMoviesResponse) {
        Log.d(TAG, "onPostExecute() called with: result = [" + getMoviesResponse + "]");
        movieResultListener.updateByMovieResults(getMoviesResponse.getResults());
        movieResultListener.hideLoading();
    }
}