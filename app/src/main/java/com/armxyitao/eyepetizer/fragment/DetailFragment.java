package com.armxyitao.eyepetizer.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author 熊亦涛
 * @time 16/7/18  21:54
 * @desc ${TODD}
 */
public class DetailFragment extends Fragment {

    private View mParent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mParent = inflater.inflate(R.layout.fragment_detail, null);
        initView();
        return mParent;
    }

    private void initView() {
        ItemList itemList = (ItemList) getArguments().getSerializable("ItemList");
        SimpleDraweeView showIv = (SimpleDraweeView) mParent.findViewById(R.id.iv_show);
        showIv.setImageURI(Uri.parse(itemList.getData().getCover().getDetail()));
//        SimpleDraweeView bottomIv = (SimpleDraweeView) mParent.findViewById(R.id.iv_bottom);
//        bottomIv.setImageURI(Uri.parse(itemList.getData().getCover().getBlurred()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
