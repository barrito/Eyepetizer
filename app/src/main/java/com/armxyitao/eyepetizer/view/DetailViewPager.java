package com.armxyitao.eyepetizer.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author 熊亦涛
 * @time 16/7/22  15:24
 * @desc ${TODD}
 */
public class DetailViewPager extends ViewPager {

    private float mStartX;
    private int mCurrentItem;
    private int mChildCount;

    public DetailViewPager(Context context) {
        super(context);
    }

    public DetailViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("geduo","child onTouchEvent");
        mChildCount = getAdapter().getCount();
        mCurrentItem = getCurrentItem();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                //手指往左滑
                if ((mCurrentItem != mChildCount - 1) && (mCurrentItem != 0)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                mStartX = endX;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(ev);
    }
}
