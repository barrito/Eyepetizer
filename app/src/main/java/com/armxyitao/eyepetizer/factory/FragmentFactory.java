package com.armxyitao.eyepetizer.factory;

import android.support.v4.app.Fragment;

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
                return new HomeFragment();
            case 2:
                return new HomeFragment();
            case 3:
                return new HomeFragment();
        }
        return null;
    }
}
