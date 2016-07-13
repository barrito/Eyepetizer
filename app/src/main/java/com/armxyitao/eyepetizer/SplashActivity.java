package com.armxyitao.eyepetizer;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.armxyitao.eyepetizer.activity.MainActivity;
import com.armxyitao.eyepetizer.util.ActivityUtil;

public class SplashActivity extends Activity {
    private TextView mIntroduceTv;
    private TextView mAppNameTv;
    private ImageView mBgIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        initView();
        initTypeface();
        startAnim();
    }

    private void initView() {
        mIntroduceTv = (TextView) findViewById(R.id.tv_app_introduce_english);
        mAppNameTv = (TextView) findViewById(R.id.tv_app_name_english);
        mBgIv = (ImageView)findViewById(R.id.iv_splash_bg);
    }

    private void initTypeface() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Lobster-1.4.otf");
        mIntroduceTv.setTypeface(typeface);
        mAppNameTv.setTypeface(typeface);
    }

    private void startAnim() {
        Animation a = AnimationUtils.loadAnimation(this,R.anim.splash_scale);
        a.setFillAfter(true);
        mBgIv.startAnimation(a);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ActivityUtil.startActivity(SplashActivity.this,MainActivity.class,true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }





}
