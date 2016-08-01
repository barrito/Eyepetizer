package com.armxyitao.eyepetizer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.factory.FragmentFactory;

import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/18  21:47
 * @desc ${TODD}
 */
public class HomeDetailAdapter extends FragmentStatePagerAdapter {
    private List<ItemList> mDatas;
    public HomeDetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createDetailFragment(mDatas.get(position));
    }

    @Override
    public int getCount() {
        return mDatas!=null?mDatas.size():0;
    }

    public void setData(List<ItemList> issueList) {
        mDatas = issueList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
