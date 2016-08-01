package com.armxyitao.eyepetizer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.IntentValues;
import com.armxyitao.eyepetizer.view.PrintTextView;
import com.astuetz.PagerSlidingTabStrip;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 熊亦涛
 * @time 16/8/1  19:26
 * @desc ${TODD}
 */
public class PanoramaDetailActivity extends AppCompatActivity {
    @Bind(R.id.vp_detail)
    ViewPager mVpDetail;
    @Bind(R.id.indicator_bottom)
    PagerSlidingTabStrip mIndicatorBottom;
    @Bind(R.id.content_bg)
    SimpleDraweeView mContentBg;
    @Bind(R.id.tv_title)
    PrintTextView mTvTitle;
    @Bind(R.id.tv_category_duration)
    PrintTextView mTvCategoryDuration;
    @Bind(R.id.divider)
    View mDivider;
    @Bind(R.id.tv_content)
    PrintTextView mTvContent;
    @Bind(R.id.tv_like)
    PrintTextView mTvLike;
    @Bind(R.id.tv_share)
    PrintTextView mTvShare;
    @Bind(R.id.tv_reply)
    PrintTextView mTvReply;
    @Bind(R.id.tv_offline)
    PrintTextView mTvOffline;
    @Bind(R.id.rl_content)
    RelativeLayout mRlContent;
    @Bind(R.id.content_container)
    RelativeLayout mContentContainer;
    @Bind(R.id.content)
    RelativeLayout mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama_detail);
        ButterKnife.bind(this);
        ArrayList<ItemList> datas = (ArrayList<ItemList>) getIntent().getSerializableExtra(IntentValues.PANORAMA_DATAS);
    }
}
