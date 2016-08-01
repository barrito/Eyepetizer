package com.armxyitao.eyepetizer.factory;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.fragment.DetailFragment;
import com.armxyitao.eyepetizer.fragment.FoundFragment;
import com.armxyitao.eyepetizer.fragment.HomeFragment;

/**
 * @author 熊亦涛
 * @time 16/7/14  17:16
 * @desc ${TODD}
 */
public class FragmentFactory {


    public static Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new FoundFragment();
            case 2:
                return new HomeFragment();
            case 3:
                return new HomeFragment();
        }
        return null;
    }

    /**
     * 详情Fragment
     *
     * @param itemList
     * @return
     */
    public static Fragment createDetailFragment(ItemList itemList) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("ItemList", itemList);
        detailFragment.setArguments(args);
        return detailFragment;
    }
}
