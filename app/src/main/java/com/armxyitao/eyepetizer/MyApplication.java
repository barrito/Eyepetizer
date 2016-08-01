package com.armxyitao.eyepetizer;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.armxyitao.eyepetizer.bean.IssueList;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.moduth.blockcanary.BlockCanary;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 熊亦涛
 * @time 16/7/14  17:59
 * @desc ${TODD}
 */
public class MyApplication extends Application {
    private static Context mContext;
    private static Handler mHandler;
    private static int mMainThread;
    public Map<Integer, IssueList> mItemMap = new LinkedHashMap<>();// key:+=count; value:封装的首页数据
    public int mPositionInItemMap=0;//当前的数组在map中是第几组

    public int getPositionInItemMap() {
        return mPositionInItemMap;
    }

    public void setPositionInItemMap(int positionInItemMap) {
        mPositionInItemMap = positionInItemMap;
    }

    public Map<Integer, IssueList> getItemMap() {
        return mItemMap;
    }

    public void setItemMap(Map<Integer, IssueList> itemMap) {
        mItemMap = itemMap;
    }

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
        //配置Fresco
        Fresco.initialize(getApplicationContext());
        //全局Context
        mContext = getApplicationContext();
        //主线程Handler
        mHandler = new Handler();
        //主线程id
        mMainThread = Process.myTid();
        //配置Logger
        initLogger();
        mItemMap = new LinkedHashMap<>();
        //初始化BlockCanary
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }

    private void initLogger() {
        Logger.init("geduo")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2)       ;         // default 0
//                .logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter
    }
}
