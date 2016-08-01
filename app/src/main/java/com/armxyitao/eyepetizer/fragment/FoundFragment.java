package com.armxyitao.eyepetizer.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.adapter.FoundRecyclerViewAdapter;
import com.armxyitao.eyepetizer.base.BaseFragment;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.NetCons;
import com.armxyitao.eyepetizer.constants.NetRequestCons;
import com.armxyitao.eyepetizer.network.FoundProtocol;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/27  17:08
 * @desc 发现
 */
public class FoundFragment extends BaseFragment {

    private RecyclerView mRv;
    private FoundRecyclerViewAdapter mFoundRecyclerViewAdapter;
    private GridLayoutManager mRvManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_found, null, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv_found);
        mRvManager = new GridLayoutManager(mActivity, 2);
        mFoundRecyclerViewAdapter = new FoundRecyclerViewAdapter(getActivity());
        mRvManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mFoundRecyclerViewAdapter.getItemViewType(position);
                if (itemViewType == FoundRecyclerViewAdapter.SQUARE) {
                    return 1;
                }
                return 2;
            }
        });
        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(mRvManager);
        mRv.setAdapter(mFoundRecyclerViewAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initProtocol(new FoundProtocol());
        mProtocol.getDataFromNet(NetRequestCons.GET_FOUND_DATA, NetCons.FOUND_URL);
    }

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case NetRequestCons.GET_FOUND_DATA_RESULT:
                handleInitData((List<ItemList>) msg.obj);
                break;
        }
    }

    private void handleInitData(List<ItemList> data) {
        List<ItemList.ItemData> datas = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            datas.add(data.get(i).getData());
        }
        mFoundRecyclerViewAdapter.setDatas(datas);
        mFoundRecyclerViewAdapter.notifyDataSetChanged();
    }

}
