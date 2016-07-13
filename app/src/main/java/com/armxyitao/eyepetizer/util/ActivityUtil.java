package com.armxyitao.eyepetizer.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * @author 熊亦涛
 * @time 16/7/12  20:26
 * @desc ${TODD}
 */
public class ActivityUtil {
    public static void startActivity(Context context, Class<?> clz,boolean isFinishSelf){
        context.startActivity(new Intent(context,clz));
        if(isFinishSelf) {
            ((Activity) context).finish();
        }
    }
}
