package com.armxyitao.eyepetizer.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.adapter.HomePagerAdapter;
import com.armxyitao.eyepetizer.event.HideAndShowEvent;
import com.armxyitao.eyepetizer.event.RecyclerViewScrollEvent;
import com.armxyitao.eyepetizer.util.TypefaceUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 熊亦涛
 * @time 16/7/14  16:48
 * @desc ${TODD}
 */
public class HomeActivity extends AppCompatActivity {
    @Bind(R.id.title)
    View mTitle;
    @Bind(R.id.vp_home)
    ViewPager mVpHome;
    @Bind(R.id.title_date)
    TextView mTitleDate;
    @Bind(R.id.tab)
    //    PagerSlidingTabStrip mTab;
            TabLayout mTab;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    public int mHeight;
    public boolean isShowing = true;//title和tab是否在显示中
    private boolean isLocked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        TypefaceUtil.setTypeface(mTvTitle);
        TypefaceUtil.setTypeface(mTitleDate);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), this);
        mVpHome.setAdapter(homePagerAdapter);
        //初始化tabLayout
        mTab.setupWithViewPager(mVpHome);
        mTab.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mTab.getTabCount(); i++) {
            TabLayout.Tab tab = mTab.getTabAt(i);
            ViewGroup view = (ViewGroup) homePagerAdapter.getTabView(i);
            if (i == 0) {
                view.getChildAt(0).setSelected(true);
            }
            if (tab != null) {
                tab.setCustomView(view);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setDate(RecyclerViewScrollEvent event) {
        mTitleDate.setText(event.mCurrentDate);
        TypefaceUtil.setTypeface(mTitleDate);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showOrHide(HideAndShowEvent event) {
        boolean isShow = event.isShow;
        //显示或者隐藏title
        doShowOrHide(isShow);

    }


    /**
     * 显示或者隐藏title
     *
     * @param isShow
     */
    private void doShowOrHide(boolean isShow) {
        //动画结束后会解锁
        if (!isLocked) {
            isLocked = true;
            if (!isShow) {
                ObjectAnimator oa1 = ObjectAnimator.ofFloat(mTitle, "translationY", 0, -mTitle.getHeight());
                oa1.setDuration(200);
                oa1.start();

                ObjectAnimator oa2 = ObjectAnimator.ofFloat(mTab, "translationY", 0, mTab.getHeight());
                oa2.setDuration(200);
                oa2.start();
                oa2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isLocked = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            } else {
                ObjectAnimator oa1 = ObjectAnimator.ofFloat(mTitle, "translationY", -mTitle.getHeight(), 0);
                oa1.setDuration(200);
                oa1.start();


                ObjectAnimator oa2 = ObjectAnimator.ofFloat(mTab, "translationY", mTab.getHeight(), 0);
                oa2.setDuration(200);
                oa2.start();
                oa2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isLocked = false;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
            isShowing = !isShowing;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
