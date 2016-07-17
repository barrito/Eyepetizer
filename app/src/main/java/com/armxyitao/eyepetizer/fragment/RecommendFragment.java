package com.armxyitao.eyepetizer.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.adapter.RecommendRvAdapter;
import com.armxyitao.eyepetizer.base.BaseFragment;
import com.armxyitao.eyepetizer.bean.HomeBean;
import com.armxyitao.eyepetizer.bean.IssueList;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.NetCons;
import com.armxyitao.eyepetizer.constants.NetRequestCons;
import com.armxyitao.eyepetizer.network.HomeProtocol;
import com.armxyitao.eyepetizer.network.IModelChangListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/14  17:16
 * @desc ${TODD}
 */
public class RecommendFragment extends BaseFragment implements IModelChangListener {

    private RecyclerView mRv;
    private List<ItemList> mRealDatas = new ArrayList<>();
    private String mNextPageUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        mRv = (RecyclerView) view.findViewById(R.id.rv_home);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HomeProtocol protocol = new HomeProtocol();
        protocol.setListener(this);
        protocol.getDataFromNet(NetCons.HOME_URL);
    }

    @Override
    public void onModelChanged(Object obj) {
        mHandler.obtainMessage(NetRequestCons.GET_HOME_DATA_RESULT, obj).sendToTarget();
    }

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case NetRequestCons.GET_HOME_DATA_RESULT:
                HomeBean bean = (HomeBean) msg.obj;
                handleHomeView(bean);
                break;
        }
    }

    private void handleHomeView(HomeBean bean) {
        //下一页的url
        mNextPageUrl = bean.getNextPageUrl();
        //一个IssueList里面有两个itemList,真实的数据封装在itemList中
        List<IssueList> issueList = bean.getIssueList();
        for (IssueList list : issueList) {
            List<ItemList> itemList = list.getItemList();//真实需要的数据
            mRealDatas.addAll(itemList);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(layoutManager);
        RecommendRvAdapter recommendRvAdapter = new RecommendRvAdapter(getActivity(), mRealDatas);
        mRv.setAdapter(recommendRvAdapter);
        recommendRvAdapter.notifyDataSetChanged();
    }
}





