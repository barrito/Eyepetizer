package com.armxyitao.eyepetizer.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.factory.FragmentFactory;

/**
 * @author 熊亦涛
 * @time 16/7/14  17:12
 * @desc ${TODD}
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_SIZE = 4;
    private Context mContext;

    private String tabTitles[];
    private int[] imageResId = {R.drawable.ic_tab_feed_selector,
            R.drawable.ic_tab_category_selector,
            R.drawable.ic_tab_pgc_selector, R.drawable.ic_tab_profile_selector};

    public HomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        tabTitles = mContext.getResources().getStringArray(R.array.tab_titles);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return PAGE_SIZE;
    }


    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_tabview, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(imageResId[position]);
        return view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
