package com.armxyitao.testtablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager)findViewById(R.id.vp);

        final String [] titles = new String[]{"Title1","Title2","Title3"};

        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                return FragmentFactory.createFragment(titles[position],MainActivity.this);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });



        tab.setupWithViewPager(vp);
    }
}
