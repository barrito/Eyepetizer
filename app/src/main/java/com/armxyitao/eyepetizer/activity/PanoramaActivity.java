package com.armxyitao.eyepetizer.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.base.BaseActivity;
import com.armxyitao.eyepetizer.constants.NetCons;
import com.armxyitao.eyepetizer.constants.NetRequestCons;
import com.armxyitao.eyepetizer.network.PanoramaProtocol;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 熊亦涛
 * @time 16/8/1  17:08
 * @desc ${TODD}
 */
public class PanoramaActivity extends BaseActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama);
        ButterKnife.bind(this);
        initProtocol(new PanoramaProtocol());
        mProtocol.getDataFromNet(NetRequestCons.GET_PANORAMA_BY_TIME, NetCons.PANORAMA_TIME_URL);
        mProtocol.getDataFromNet(NetRequestCons.GET_PANORAMA_BY_SHARE, NetCons.PANORAMA_SHARE_URL);
    }

}
