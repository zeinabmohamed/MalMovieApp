package com.gamila.zm.malmovieapp;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Zeinab Mohamed on 9/30/2016.
 */

public class AppConstants {

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
