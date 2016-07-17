package com.armxyitao.eyepetizer.util;

import android.graphics.Typeface;
import android.widget.TextView;

/**
 * @author 熊亦涛
 * @time 16/7/14  17:28
 * @desc ${TODD}
 */
public class TypefaceUtil {
    public static void setTypeface(TextView tv, String typePath){
        Typeface typeface = Typeface.createFromAsset(Utils.getContext().getAssets(), typePath);
        tv.setTypeface(typeface);
    }

    public static void setTypeface(TextView tv){
        Typeface typeface = Typeface.createFromAsset(Utils.getContext().getAssets(), "fonts/Lobster-1.4.otf");
        tv.setTypeface(typeface);
    }
}
