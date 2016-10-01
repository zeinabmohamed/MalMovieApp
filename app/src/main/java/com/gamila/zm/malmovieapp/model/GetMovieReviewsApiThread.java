package com.gamila.zm.malmovieapp.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.gamila.zm.malmovieapp.AppConstants;
import com.gamila.zm.malmovieapp.R;
import com.gamila.zm.malmovieapp.utils.NetworkUtil;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Zeinab Mohamed on 10/1/2016.
 */

public class GetMovieReviewsApiThread extends AsyncTask<Long, Object, Object> {

    private static final String TAG = GetMovieVideosApiThread.class.getSimpleName();
    private final Context context;
    private MovieReviewsResultListener movieReviewsResultListener;

    public GetMovieReviewsApiThread(Context context , MovieReviewsResultListener movieReviewsResultListener) {
        this.movieReviewsResultListener = movieReviewsResultListener;
        this.context= context;
    }


    @Override
    protected void onPreExecute() {
        movieReviewsResultListener.showLoading();
    }

    protected Object doInBackground(Long... params) {
        Log.d(TAG, "doInBackground() called with: params " + params[0]);
        // handle request
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String moviesListJsonStr = null;
        if (NetworkUtil.isOnline(context)) {
        try {
                URL url = new URL(String.format(AppConstants.MOVIE_REVIEWS_URL,params[0]));
            Log.i(TAG, "doInBackground: MOVIE_REVIEWS_URL "+url.toString());
                // Create the request to themoviedb, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return new Exception(context.getString(R.string.server_error_please_try_again_later));
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
                    return new Exception(context.getString(R.string.server_error_please_try_again_later));
                }
                moviesListJsonStr = buffer.toString();
                Gson gson = new Gson();
                GetMovieReviewsResponse response = gson.fromJson(moviesListJsonStr, GetMovieReviewsResponse.class);
                return response;
            }catch(IOException e){
                Log.e(TAG, "Error ", e);
                // If the code didn't successfully .
                return new Exception(context.getString(R.string.server_error_please_try_again_later));
            }finally{
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
        }else{
            return new Exception(context.getString(R.string.please_check_your_internet_connection));
        }

    }

    @Override
    protected void onPostExecute(Object result) {
        Log.d(TAG, "onPostExecute() called with: result = [" + result + "]");
        if(result !=null){
            if(result instanceof  GetMovieReviewsResponse){
                movieReviewsResultListener.updateByMovieReviewsResults(((GetMovieReviewsResponse)result).getResults());
                movieReviewsResultListener.hideLoading();

            }else if(result instanceof Exception){
                movieReviewsResultListener.hideLoading();
                movieReviewsResultListener.showError((Exception) result);
            }
        }else {
            movieReviewsResultListener.hideLoading();
        }
    }

        public interface MovieReviewsResultListener {

        void showLoading();

        void hideLoading();

        void showError(Exception result);

        void updateByMovieReviewsResults(List<GetMovieReviewsResponse.ReviewMovie> results);
    }

}