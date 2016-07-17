package com.armxyitao.eyepetizer.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Process;

import com.armxyitao.eyepetizer.MyApplication;

/**
 * @author 熊亦涛
 * @time 16/7/14  17:59
 * @desc ${TODD}
 */
public class Utils {
    /**
     * 获取全局Context
     * @return
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 获取全局的Resources
     *
     * @return
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取字符串
     *
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }
    public static String getString(int resId,Object...args) {
        return getResources().getString(resId,args);
    }

    /**
     * 获取字符串数组
     *
     * @param resId
     * @return
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取颜色
     *
     * @param resId
     * @return
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取包名
     *
     * @return
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * 获取Handler
     * @return
     */
    public static Handler getHandler(){
        return MyApplication.getHandler();
    }

    /**
     * 安全的执行任务,确保在主线程执行
     */
    public static void postTaskSafely(Runnable task){
        //获取当前线程的ID
        int currentThreadId = Process.myTid();
        //判断当前线程id是否等于主线程id
        if(currentThreadId==MyApplication.getMainThread()) {
            //直接执行
            task.run();
        }else {
            getHandler().post(task);
        }
    }

    /**
     * dp-->px
     * @param dip
     * @return
     */
    public static int dip2px(int dip){
        //px/dp=density
        float density = getResources().getDisplayMetrics().density;
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
        float density = getResources().getDisplayMetrics().density;
        //加0.5f减少精度的丢失
        return ((int)(px/density+0.5f));
    }
}
