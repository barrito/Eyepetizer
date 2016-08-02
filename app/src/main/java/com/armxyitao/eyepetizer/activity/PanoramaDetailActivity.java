package com.armxyitao.eyepetizer.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.adapter.PanoramaDetailAdapter;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.IntentValues;
import com.armxyitao.eyepetizer.util.TimeUtil;
import com.armxyitao.eyepetizer.view.PrintTextView;
import com.astuetz.PagerSlidingTabStrip;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 熊亦涛
 * @time 16/8/1  19:26
 * @desc ${TODD}
 */
public class PanoramaDetailActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
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
    private ArrayList<ItemList> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama_detail);
        ButterKnife.bind(this);
        mDatas = (ArrayList<ItemList>) getIntent().getSerializableExtra(IntentValues.PANORAMA_DATAS);
        int position = getIntent().getIntExtra(IntentValues.PANORAMA_POSITION,0);
        initView(position);

    }

    private void initView(int position) {
        mVpDetail.setAdapter(new PanoramaDetailAdapter(getSupportFragmentManager(),mDatas));
        //数据
        ItemList.ItemData data = mDatas.get(position).getData();
        //初始化TextView
        initTextView(data);
        mContentBg.setImageURI(Uri.parse(data.getCover().getBlurred()));
        mVpDetail.setCurrentItem(position);
        mVpDetail.addOnPageChangeListener(this);
    }

    /**
     * 初始化TextView,打字效果
     *
     * @param data
     */
    private void initTextView(ItemList.ItemData data) {
        mTvTitle.start(data.getTitle());
        mTvContent.start(data.getDescription(), 2, false);
        mTvCategoryDuration.start("#" + data.getCategory() + "  /  " + TimeUtil.long2String(data.getDuration()),80);
        mTvLike.start(data.getConsumption().getCollectionCount() + "");
        mTvShare.start(data.getConsumption().getShareCount() + "");
        mTvReply.start(data.getConsumption().getReplyCount() + "");
        mTvOffline.start("缓存");

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        initTextView(mDatas.get(position).getData());
        mContentBg.setImageURI(Uri.parse(mDatas.get(position).getData().getCover().getBlurred()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
