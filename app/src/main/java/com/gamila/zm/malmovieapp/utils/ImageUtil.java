package com.gamila.zm.malmovieapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.gamila.zm.malmovieapp.AppConstants;
import com.gamila.zm.malmovieapp.BuildConfig;
import com.squareup.picasso.Picasso;

/**
 * Created by Zeinab Mohamed on 9/30/2016.
 */

public class ImageUtil {


    private static ImageUtil imageUtil;

    public static ImageUtil getInstance() {
        if (imageUtil == null) {
            imageUtil = new ImageUtil();
        }
        return imageUtil;
    }

    private ImageUtil() {
        //no instance
    }

    public void loadImageByFullUrlInImageView(Context context, String imageStringUrl, ImageView imageView) {
        Picasso.with(context).load(imageStringUrl).into(imageView);
    }

    public void loadImageByImageNameInImageView(Context context, String imageName, ImageView imageView) {
        Picasso.with(context).load(AppConstants.IMAGE_BASE_URL + AppConstants.IMAGE_SIZE.W_185 +
                imageName).into(imageView);
    }


}
