package com.armxyitao.testcoordinatorlayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



//
//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle("Title");
//        RecyclerView rvToDoList = (RecyclerView) findViewById(R.id.rvToDoList);
//        rvToDoList.setLayoutManager(new LinearLayoutManager(this));
//        rvToDoList.setAdapter(new MyAdapter());


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


    class MyHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
    class MyAdapter extends RecyclerView.Adapter<MyHolder>{

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(getApplicationContext());
            return new MyHolder(textView);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.tv.setText("文本"+position);
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }
}
