package com.armxyitao.eyepetizer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.adapter.PanoramaAdapter;
import com.armxyitao.eyepetizer.base.BaseActivity;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.bean.PanoramaInfo;
import com.armxyitao.eyepetizer.constants.IntentValues;
import com.armxyitao.eyepetizer.constants.NetCons;
import com.armxyitao.eyepetizer.constants.NetRequestCons;
import com.armxyitao.eyepetizer.network.PanoramaProtocol;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 熊亦涛
 * @time 16/8/1  17:08
 * @desc ${TODD}
 */
public class PanoramaActivity extends BaseActivity
        implements View.OnClickListener, PanoramaAdapter.OnItemClickListener {
    @Bind(R.id.title_date)
    TextView mTitleDate;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_share)
    ImageView mIvShare;
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.sort_by_time)
    TextView mSortByTime;
    @Bind(R.id.sort_by_share)
    TextView mSortByShare;
    @Bind(R.id.rv_panorama)
    RecyclerView mRvPanorama;
    private PanoramaAdapter mPanoramaAdapter;
    private List<ItemList> mTimeDatas = new ArrayList<>();
    private List<ItemList> mShareDatas = new ArrayList<>();
    private List<ItemList> mCurrentDatas;
    private int currentSort = SORT_BY_TIME;       //当前排序方式
    private static final int SORT_BY_TIME = 0;    //按时间排序
    private static final int SORT_BY_SHARE = 1;   //按分享排序
    private boolean isLoading = false;
    private String timeNextUrl;
    private String shareNextUrl;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama);
        ButterKnife.bind(this);
        initView();
        getDataFromNet();
    }

    private void initView() {
        mPanoramaAdapter = new PanoramaAdapter(this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRvPanorama.setLayoutManager(mLayoutManager);
        mRvPanorama.setHasFixedSize(true);
        mRvPanorama.setAdapter(mPanoramaAdapter);
        //添加点击监听器
        mSortByTime.setOnClickListener(this);
        mSortByShare.setOnClickListener(this);
        //RecyclerView滑动监听器
        mRvPanorama.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (mLayoutManager.findLastVisibleItemPosition() == mCurrentDatas.size() - 1) {
                    getNextDataFromNet();
                }
            }
        });
        //item点击事件
        mPanoramaAdapter.setOnItemClickListener(this);
    }

    /**
     * 获取下一组数据
     */
    private void getNextDataFromNet() {
        if (!isLoading) {
            isLoading = true;
            if (currentSort == SORT_BY_TIME) {
                if(TextUtils.isEmpty(timeNextUrl)) {
                    Toast.makeText(getApplicationContext(),"没有更多啦",Toast.LENGTH_SHORT).show();
                    return;
                }
                mProtocol.getDataFromNet(NetRequestCons.GET_PANORAMA_BY_TIME,
                        timeNextUrl);
            } else {
                if(TextUtils.isEmpty(shareNextUrl)) {
                    Toast.makeText(getApplicationContext(),"没有更多啦",Toast.LENGTH_SHORT).show();
                    return;
                }
                mProtocol.getDataFromNet(NetRequestCons.GET_PANORAMA_BY_SHARE,
                        shareNextUrl);
            }
        }
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet() {
        initProtocol(new PanoramaProtocol());
        mProtocol.getDataFromNet(NetRequestCons.GET_PANORAMA_BY_TIME,
                NetCons.PANORAMA_TIME_URL);
        mProtocol.getDataFromNet(NetRequestCons.GET_PANORAMA_BY_SHARE,
                NetCons.PANORAMA_SHARE_URL);
    }

    @Override
    public void handlerMessage(Message msg) {
        PanoramaInfo info = (PanoramaInfo) msg.obj;
        switch (msg.what) {
            case NetRequestCons.GET_PANORAMA_BY_TIME_RESULT:
                //size!=0说明不是第一次加载
                if (mTimeDatas.size() != 0) {
                    isLoading = false;
                }
                handleInitTimeView(info);
                break;
            case NetRequestCons.GET_PANORAMA_BY_SHARE_RESULT:
                //size!=0说明不是第一次加载
                if (mShareDatas.size() != 0) {
                    isLoading = false;
                }
                handleInitShareView(info);
                break;
        }
    }

    /**
     * 初始化分享排序数据
     *
     * @param info
     */
    private void handleInitShareView(PanoramaInfo info) {
        shareNextUrl = info.nextPageUrl;
        mShareDatas.addAll(info.itemList);
        if (currentSort == SORT_BY_SHARE) {
            mPanoramaAdapter.addData(info.itemList);
        }
    }

    /**
     * 初始化时间排序数据
     *
     * @param info
     */
    private void handleInitTimeView(PanoramaInfo info) {
        timeNextUrl = info.nextPageUrl;
        mTimeDatas.addAll(info.itemList);
        mCurrentDatas = mTimeDatas;
        if (currentSort == SORT_BY_TIME) {
            mPanoramaAdapter.addData(info.itemList);
        }
        mPanoramaAdapter.addData(info.itemList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sort_by_time:
                mLayoutManager.scrollToPosition(0);
                isLoading=false;
                mCurrentDatas = mTimeDatas;
                currentSort = SORT_BY_TIME;
                mPanoramaAdapter.setData(mTimeDatas);
                break;
            case R.id.sort_by_share:
                mLayoutManager.scrollToPosition(0);
                isLoading=false;
                mCurrentDatas = mShareDatas;
                currentSort = SORT_BY_SHARE;
                mPanoramaAdapter.setData(mShareDatas);
                break;
        }
    }

    /**
     *
     * @param position
     * @param mDatas
     */
    @Override
    public void onItemClick(int position, List<ItemList> mDatas) {
        Intent intent = new Intent(getApplicationContext(),PanoramaDetailActivity.class);
        intent.putExtra(IntentValues.PANORAMA_DATAS,(ArrayList)mDatas);
        intent.putExtra(IntentValues.PANORAMA_POSITION,position);
        startActivity(intent);
    }
}
