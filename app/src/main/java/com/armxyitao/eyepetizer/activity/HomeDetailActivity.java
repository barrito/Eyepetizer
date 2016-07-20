package com.armxyitao.eyepetizer.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.adapter.HomeDetailAdapter;
import com.armxyitao.eyepetizer.bean.IssueList;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.IntentValues;
import com.armxyitao.eyepetizer.util.ScreenUtil;
import com.armxyitao.eyepetizer.util.TimeUtil;
import com.armxyitao.eyepetizer.view.TypeTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 熊亦涛
 * @time 16/7/18  16:10
 * @desc ${TODD}
 */
public class HomeDetailActivity extends FragmentActivity
        implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    @Bind(R.id.vp_detail)
    ViewPager mVpDetail;
    @Bind(R.id.indicator_bottom)
    HorizontalScrollView mIndicatorBottom;
    @Bind(R.id.tv_title)
    TypeTextView mTvTitle;
    @Bind(R.id.tv_category_duration)
    TypeTextView mTvCategoryDuration;
    @Bind(R.id.tv_content)
    TypeTextView mTvContent;
    @Bind(R.id.tv_like)
    TypeTextView mTvLike;
    @Bind(R.id.tv_share)
    TypeTextView mTvShare;
    @Bind(R.id.tv_reply)
    TypeTextView mTvReply;
    @Bind(R.id.tv_offline)
    TypeTextView mTvOffline;
    @Bind(R.id.content_bg)
    SimpleDraweeView mContentBg;
    @Bind(R.id.rl_content)
    RelativeLayout mRlcontent;
    private IssueList mIssueList;
    private float mStartX;
    private int mScreenWidth = ScreenUtil.getScreenWidht();

    private int mCurrentPostion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //获取Intent传值
        mIssueList = (IssueList) getIntent().getSerializableExtra("IssueList");
        int position = getIntent().getIntExtra(IntentValues.DETAIL_CURRENT_POSITION, 0);
        //数据
        ItemList.ItemData data = mIssueList.getItemList().get(position).getData();
        //初始化TextView
        initTextView(data);
        mContentBg.setImageURI(Uri.parse(data.getCover().getBlurred()));

        HomeDetailAdapter homeDetailAdapter = new HomeDetailAdapter(getSupportFragmentManager());
        homeDetailAdapter.setData(mIssueList.getItemList());
        mVpDetail.setAdapter(homeDetailAdapter);
        mVpDetail.setCurrentItem(position);
        mVpDetail.addOnPageChangeListener(this);
        mVpDetail.setOnTouchListener(this);
    }

    /**
     * 初始化TextView,打印效果
     *
     * @param data
     */
    private void initTextView(ItemList.ItemData data) {
        mTvTitle.start(data.getTitle(), 50);
        mTvContent.start(data.getDescription());
        mTvCategoryDuration.start("#" + data.getCategory() + "  /  " + TimeUtil.long2String(data.getDuration()), 80);
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
        mCurrentPostion = position;
        ItemList.ItemData data =  mIssueList.getItemList().get(position).getData();
        initTextView(data);
        mContentBg.setImageURI(Uri.parse(data.getCover().getBlurred()));

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = (int) event.getX();
                float offX = moveX - mStartX;
                //如果是最后一页则不处理alpha动画
                if(mCurrentPostion==mIssueList.getItemList().size()-1) {
                    return false;
                }
                if(mCurrentPostion==0) {
                    //TODO
                    return false;
                }
                float alpha = Math.abs(offX) / mScreenWidth * 1.0f;
                mRlcontent.setAlpha(1-alpha);
//                if(Math.abs(offX)>mScreenWidth/2) {
//                    if(offX<0) {
//                        mContentBg.setImageURI(Uri.parse(mIssueList.getItemList().get(mCurrentPostion+1).getData().getCover().getBlurred()));
//                    }else {
//                        mContentBg.setImageURI(Uri.parse(mIssueList.getItemList().get(mCurrentPostion-1).getData().getCover().getBlurred()));
//                    }
//                }
                break;
            case MotionEvent.ACTION_UP:

                mRlcontent.setAlpha(1);
                break;
        }
        return false;
    }
}
