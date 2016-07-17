package com.armxyitao.eyepetizer;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author 熊亦涛
 * @time 16/7/14  17:59
 * @desc ${TODD}
 */
public class MyApplication extends Application {
    private static Context mContext ;
    private static Handler mHandler ;
    private static int mMainThread;

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static void setHandler(Handler handler) {
        mHandler = handler;
    }

    public static int getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(int mainThread) {
        mMainThread = mainThread;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
        //全局Context
        mContext = getApplicationContext();
        //主线程Handler
        mHandler = new Handler();
        //主线程id
        mMainThread = Process.myTid();
    }
}
