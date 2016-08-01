package com.armxyitao.eyepetizer.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armxyitao.eyepetizer.MyApplication;
import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.adapter.HomeRecyclerViewAdapter;
import com.armxyitao.eyepetizer.base.BaseFragment;
import com.armxyitao.eyepetizer.bean.HomeBean;
import com.armxyitao.eyepetizer.bean.IssueList;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.NetCons;
import com.armxyitao.eyepetizer.constants.NetRequestCons;
import com.armxyitao.eyepetizer.event.HideAndShowEvent;
import com.armxyitao.eyepetizer.event.HomeRecyclerViewItemChangeEvent;
import com.armxyitao.eyepetizer.event.RecyclerViewScrollEvent;
import com.armxyitao.eyepetizer.network.HomeProtocol;
import com.armxyitao.eyepetizer.network.IModelChangListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 熊亦涛
 * @time 16/7/14  17:16
 * @desc ${TODD}
 */
public class HomeFragment extends BaseFragment implements IModelChangListener {

    private RecyclerView mRv;
    private List<ItemList> mRealDatas = new ArrayList<>();
    private List<Integer> mDataSizes = new ArrayList<>();
    private String mNextPageUrl;
    private LinearLayoutManager mLayoutManager;
    private List<IssueList> mIssueLists = new ArrayList<>();//封装了title左边的date
    private HomeRecyclerViewAdapter mRecommendRvAdapter;
    private int mCount = 0;
    private boolean isLoading = false;
    private Map<Integer, IssueList> mItemMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv_home);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(mLayoutManager);
        mRecommendRvAdapter = new HomeRecyclerViewAdapter(getActivity());
        mRv.setAdapter(mRecommendRvAdapter);
        mItemMap = ((MyApplication) getActivity().getApplication()).mItemMap;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initProtocol(new HomeProtocol());
        mProtocol.getDataFromNet(NetRequestCons.GET_HOME_DATA, NetCons.HOME_URL);
    }

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case NetRequestCons.GET_HOME_DATA_RESULT:
                handleLoadData((HomeBean) msg.obj);
                break;
            case NetRequestCons.GET_MORE_HOME_DATA_RESULT:
                handleLoadMoreData((HomeBean) msg.obj);
                break;
        }
    }

    private void handleLoadMoreData(HomeBean bean) {
        //下一页的url
        mNextPageUrl = bean.getNextPageUrl();
        //一个IssueList里面有两个itemList,真实的数据封装在itemList中
        List<IssueList> issueList = bean.getIssueList();
        mIssueLists.addAll(issueList);//封装了date
        //        Map<Integer, IssueList> map = new LinkedHashMap<>();
        for (int i = 0; i < issueList.size(); i++) {
            List<ItemList> itemList = issueList.get(i).getItemList();//真实需要的数据
            mRealDatas.addAll(itemList);

            mItemMap.put(mCount, issueList.get(i));//存放所所有数据count之和和list
            mCount += issueList.get(i).getCount();
            mDataSizes.add(mCount);
        }
        //                mRecommendRvAdapter.addDataMap(map);//更新map
        mRecommendRvAdapter.notifyDataSetChanged();
        isLoading = false;
    }

    /**
     * 第一次加载数据
     *
     * @param bean
     */
    private void handleLoadData(HomeBean bean) {
        //下一页的url
        mNextPageUrl = bean.getNextPageUrl();
        //一个IssueList里面有两个itemList,真实的数据封装在itemList中
        List<IssueList> issueList = bean.getIssueList();
        mIssueLists.addAll(issueList);//封装了date
        //key为每个issueList的+=count,value为对应的issueList
        //        Map<Integer, IssueList> map = new LinkedHashMap<>();
        for (int i = 0; i < issueList.size(); i++) {
            List<ItemList> itemList = issueList.get(i).getItemList();//真实需要的数据
            mRealDatas.addAll(itemList);
            //            for (int j = 0; j < itemList.size(); j++) {
            //                if (!"video".equals(itemList.get(i).getType())) {
            //                    itemList.remove(i);
            //                    j--;
            //                    mCount--;
            //                }
            //            }
            mItemMap.put(mCount, issueList.get(i));//第一个key=0,第二个key=第一个IssueList的count...
            mCount += issueList.get(i).getCount();
            mDataSizes.add(mCount);
        }
        mRecommendRvAdapter.setData(mRealDatas);
        mRecommendRvAdapter.notifyDataSetChanged();
        //        mRecommendRvAdapter.addDataMap(map);
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //改变Title的日期
                changeDate();
                //手指上滑正数,下滑负数
                //如果title和tab正在展示
                if (mActivity.isShowing) {
                    if (dy > 10) {
                        EventBus.getDefault().post(new HideAndShowEvent(false));
                    }
                } else {
                    if (dy < -10) {
                        EventBus.getDefault().post(new HideAndShowEvent(true));
                    }
                }

                if (mLayoutManager.findLastVisibleItemPosition() == mRealDatas.size() - 3) {
                    //如果当前没在请求中则发送请求
                    if (!isLoading) {
                        mProtocol.getDataFromNet(NetRequestCons.GET_MORE_HOME_DATA, mNextPageUrl);
                        isLoading = true;
                    }
                }
            }
        });
        mRecommendRvAdapter.notifyDataSetChanged();
    }

    /**
     * 改变title的日期
     */

    private void changeDate() {
        int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
        for (int i = 0; i < mDataSizes.size(); i++) {
            if (firstVisibleItemPosition <= mDataSizes.get(i)) {
                if (i == 0) {
                    EventBus.getDefault().post(new RecyclerViewScrollEvent("Today"));
                } else {
                    EventBus.getDefault().post(new RecyclerViewScrollEvent(mIssueLists.get(i).getItemList().get(0).getData().getText()));
                }
                return;
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(HomeRecyclerViewItemChangeEvent message) {
        mLayoutManager.scrollToPosition(message.position);
    }
}





