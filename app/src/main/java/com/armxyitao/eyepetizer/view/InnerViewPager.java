package com.armxyitao.eyepetizer.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author 熊亦涛
 * @time 16/8/1  15:43
 * @desc ${TODD}
 */
public class InnerViewPager extends ViewPager {
    public InnerViewPager(Context context) {
        super(context);
    }

    public InnerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN :
//
//                break;
//        }
        return true;
    }
}
