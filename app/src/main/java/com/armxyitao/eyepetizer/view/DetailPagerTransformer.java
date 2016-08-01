package com.armxyitao.eyepetizer.view;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 熊亦涛
 * @time 16/7/20  14:09
 * @desc ${TODD}
 */
public class DetailPagerTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    /**
     * Apply a property transformation to the given page.
     *
     * @param view     Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     *                 page position to the right, and -1 is one page position to the left.
     */
    @Override
    public void transformPage(View view, float position) {
        //postion 0表示在屏幕中间,-1表示在屏幕左边,1表示在屏幕右边
        View bottom = ((ViewGroup) view).getChildAt(0);
        View top = ((ViewGroup) view).getChildAt(1);
        int pageWidth = view.getWidth();

        topTransformer(position, bottom, pageWidth);

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            top.setAlpha(0);
        }else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            top.setAlpha(1);
            top.setTranslationX(0);
            top.setScaleX(1);
            top.setScaleY(1);

        }else if (position <= 1) { // (0,1]
            // Fade the page out.
//            top.setAlpha(1 - position);
            top.setTranslationX(pageWidth * -position);


            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
//            top.setScaleX(scaleFactor);
//            top.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            top.setAlpha(0);
        }
    }

    private void topTransformer(float position, View bottom, int pageWidth) {
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            bottom.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
//            bottom.setAlpha(1);
//            bottom.setTranslationX(0);
//            bottom.setScaleX(1);
//            bottom.setScaleY(1);
            bottom.setTranslationX(pageWidth * -position);
            bottom.setAlpha(1-position);
//            bottom.setTranslationX(0);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            bottom.setAlpha(1 - position);

            // Counteract the default slide transition
            bottom.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
//            bottom.setScaleX(scaleFactor);
//            bottom.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            bottom.setAlpha(0);
        }
    }
}
