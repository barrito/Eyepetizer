package com.armxyitao.eyepetizer.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.transition.Explode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.armxyitao.eyepetizer.MyApplication;
import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.adapter.HomeDetailAdapter;
import com.armxyitao.eyepetizer.bean.IssueList;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.IntentValues;
import com.armxyitao.eyepetizer.event.HomeRecyclerViewItemChangeEvent;
import com.armxyitao.eyepetizer.listener.IDetailContainerListener;
import com.armxyitao.eyepetizer.util.ScreenUtil;
import com.armxyitao.eyepetizer.util.TimeUtil;
import com.armxyitao.eyepetizer.util.Utils;
import com.armxyitao.eyepetizer.view.DetailContainer;
import com.armxyitao.eyepetizer.view.PrintTextView;
import com.astuetz.PagerSlidingTabStrip;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 熊亦涛
 * @time 16/7/18  16:10
 * @desc ${TODD}
 */
public class HomeDetailActivity extends FragmentActivity
        implements ViewPager.OnPageChangeListener, View.OnTouchListener, IDetailContainerListener {
    @Bind(R.id.container)
    DetailContainer mContainer;          //最外层容器
    @Bind(R.id.content_container)
    RelativeLayout mContentContainer;   //内容容器
    @Bind(R.id.vp_detail)
    ViewPager mVpDetail;
    @Bind(R.id.indicator_bottom)
    PagerSlidingTabStrip mIndicatorBottom;
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
    @Bind(R.id.content_bg)
    SimpleDraweeView mContentBg;
    @Bind(R.id.rl_content)
    RelativeLayout mRlcontent;

    private float mStartX;
    private int mScreenWidth = ScreenUtil.getScreenWidth();
    private int mCurrentPosition;//当前选中页面的位置    0-5;
    private List<ItemList> mItemList = new ArrayList<>();
    private HomeDetailAdapter mHomeDetailAdapter;

    private int textHeadCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //共享元素动画
//        ChangeBounds c = new ChangeBounds();
//        c.setDuration(500);
//        getWindow().setSharedElementEnterTransition(c);
        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        mHomeDetailAdapter = new HomeDetailAdapter(getSupportFragmentManager());
        mVpDetail.setAdapter(mHomeDetailAdapter);
        mVpDetail.addOnPageChangeListener(this);
        mVpDetail.setOnTouchListener(this);
        //设置回调
        mContainer.setIDetailContainerListener(this);
        //获取Intent传值
        IssueList issueList = (IssueList) getIntent().getSerializableExtra("IssueList");
        int position = getIntent().getIntExtra(IntentValues.DETAIL_CURRENT_POSITION, 0);
        //初始化数据
        initData(issueList.getItemList(), position);
    }

    private void initData(List<ItemList> itemList, int position) {
        mItemList.clear();
        mItemList.addAll(itemList);
        //处理数据,把非视频的数据移除
        for (int i = 0; i < mItemList.size(); i++) {
            //            Logger.d(mItemList.get(i).getType());
            if (!"video".equals(mItemList.get(i).getType())) {
                mItemList.remove(i);
                position = position == 0 ? 0 : position - 1;
                textHeadCount++;
                i--;
            }
        }
        //重置布局
        mContainer.resetLayout();
        //数据
        ItemList.ItemData data = mItemList.get(position).getData();
        //        Logger.d(itemList);
        //初始化TextView
        initTextView(data);
        mContentBg.setImageURI(Uri.parse(data.getCover().getBlurred()));
        mHomeDetailAdapter.setData(mItemList);
        mVpDetail.setCurrentItem(position);

    }

    /**
     * 初始化TextView,打字效果
     *
     * @param data
     */
    private void initTextView(ItemList.ItemData data) {
        mTvTitle.start(data.getTitle());
        mTvContent.start(data.getDescription(), 2, false);
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
        mCurrentPosition = position;
        ItemList.ItemData data = mItemList.get(position).getData();
        initTextView(data);
        mContentBg.setImageURI(Uri.parse(data.getCover().getBlurred()));
        //获取当前数据在主页的位置
        Integer i = getPositionInHome(position);
        //发送事件,提示Home页面的RecyclerView修改当前位置
        EventBus.getDefault().post(new HomeRecyclerViewItemChangeEvent(i));

    }

    @NonNull
    private Integer getPositionInHome(int position) {
        Set<Integer> keys = ((MyApplication) getApplication()).mItemMap.keySet();
        List<Integer> positions = new ArrayList<>(keys);
        Integer i = positions.get(((MyApplication) getApplication()).mPositionInItemMap);
        i = i + position + textHeadCount;
        return i;
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
                if (mCurrentPosition == mItemList.size() - 1) {
                    return false;
                }
                if (mCurrentPosition == 0) {
                    return false;
                }
                float alpha = Math.abs(offX) / mScreenWidth * 1.0f;
                mRlcontent.setAlpha(1 - alpha);

                break;
            case MotionEvent.ACTION_UP:
                mRlcontent.setAlpha(1);
                break;
        }
        return false;
    }

    @Override
    public void onFingerUp(boolean isLoadNext) {
        if (isLoadNext) {
            //            Logger.d(((MyApplication) Utils.getContext()).mPositionInItemMap+"   "+(((MyApplication)getApplication()).mItemMap.size() - 1));
            //如果不是最后一页直接刷新数据即可
            if (((MyApplication) getApplication()).mPositionInItemMap != ((MyApplication) Utils.getContext()).mItemMap.size() - 1) {
                ((MyApplication) getApplication()).mPositionInItemMap += 1;
                Set<Integer> keys = ((MyApplication) getApplication()).mItemMap.keySet();
                List<Integer> list = new ArrayList<>(keys);
                IssueList issueList = ((MyApplication) getApplication()).mItemMap.get(list.get(((MyApplication) Utils.getContext()).mPositionInItemMap));
                //重新初始化数据
                initData(issueList.getItemList(), 1);
            }
        } else {
            if (((MyApplication) Utils.getContext()).mPositionInItemMap != 0) {
                ((MyApplication) Utils.getContext()).mPositionInItemMap -= 1;
                Set<Integer> keys = ((MyApplication) Utils.getContext()).mItemMap.keySet();
                List<Integer> list = new ArrayList<>(keys);
                IssueList issueList = ((MyApplication) Utils.getContext()).mItemMap.get(list.get(((MyApplication) Utils.getContext()).mPositionInItemMap));
                //重新初始化数据
                initData(issueList.getItemList(), 0);
                //刷新fragment
                //EventBus.getDefault().post(new RefreshFragmentEvent(issueList.getItemList().get(0)));
            } else {

            }
        }
    }
}
