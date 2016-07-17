package com.armxyitao.eyepetizer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.adapter.HomePagerAdapter;
import com.armxyitao.eyepetizer.util.TypefaceUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 熊亦涛
 * @time 16/7/14  16:48
 * @desc ${TODD}
 */
public class HomeActivity extends AppCompatActivity implements View.OnTouchListener {
    @Bind(R.id.vp_home)
    ViewPager mVpHome;
    @Bind(R.id.title_date)
    TextView mTitleDate;
    @Bind(R.id.tab)
    TabLayout mTab;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        TypefaceUtil.setTypeface(mTvTitle);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), this);
        mVpHome.setAdapter(homePagerAdapter);
        mTab.setupWithViewPager(mVpHome);
        mTab.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mTab.getTabCount(); i++) {
            TabLayout.Tab tab = mTab.getTabAt(i);
            tab.setCustomView(homePagerAdapter.getTabView(i));
        }
        mVpHome.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {



        return false;
    }
}
