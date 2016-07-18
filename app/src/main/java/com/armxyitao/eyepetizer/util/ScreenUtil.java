package com.armxyitao.eyepetizer.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * @author 熊亦涛
 * @time 16/7/17  11:41
 * @desc ${TODD}
 */
public class ScreenUtil {
    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidht() {
        WindowManager wm = (WindowManager) Utils.getContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }
    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) Utils.getContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * dp-->px
     * @param dip
     * @return
     */
    public static int dip2px(int dip){
        //px/dp=density
        float density = Utils.getContext().getResources().getDisplayMetrics().density;
        //加0.5f减少精度的丢失
        return ((int)(dip*density+0.5f));
    }
    /**
     * px-->dip
     * @param px
     * @return
     */
    public static int px2dip(int px){
        //px/dp=density
        float density = Utils.getContext().getResources().getDisplayMetrics().density;
        //加0.5f减少精度的丢失
        return ((int)(px/density+0.5f));
    }
}
