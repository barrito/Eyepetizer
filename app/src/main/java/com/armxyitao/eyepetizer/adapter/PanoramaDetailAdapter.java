package com.armxyitao.eyepetizer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.factory.FragmentFactory;

import java.util.ArrayList;

/**
 * @author 熊亦涛
 * @time 16/8/2  01:32
 * @desc ${TODD}
 */
public class PanoramaDetailAdapter extends FragmentPagerAdapter {
    private ArrayList<ItemList> mDatas;

    public PanoramaDetailAdapter(FragmentManager fm, ArrayList<ItemList> datas) {
        super(fm);
        mDatas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createDetailFragment(mDatas.get(position));
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
