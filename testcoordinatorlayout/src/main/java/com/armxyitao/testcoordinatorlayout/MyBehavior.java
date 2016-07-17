package com.armxyitao.testcoordinatorlayout;

/**
 * @author 熊亦涛
 * @time 16/7/14  11:40
 * @desc ${TODD}
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class MyBehavior extends CoordinatorLayout.Behavior{
private int targetId;
    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.myapp);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            if(a.getIndex(i) == R.styleable.myapp_target){
                targetId = a.getResourceId(attr, -1);
            }
        }
        a.recycle();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        return dependency.getId()==R.id.vp;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if(dy>0) {
            Animation a = new TranslateAnimation(1,0,1,0,1,0,1,child.getHeight());
            a.setDuration(1000);
            child.startAnimation(a);
        }else {
            Animation a = new TranslateAnimation(1,0,1,0,1,child.getHeight(),1,0);
            a.setDuration(1000);
            child.startAnimation(a);
        }
    }
}