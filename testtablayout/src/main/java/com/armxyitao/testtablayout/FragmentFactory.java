package com.armxyitao.testtablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author 熊亦涛
 * @time 16/7/14  09:00
 * @desc ${TODD}
 */
public class FragmentFactory {

    public static Fragment createFragment(String text, Context context) {
        MyFragment fragment = new MyFragment(context, text);
        return fragment;
    }

    public static class MyFragment extends Fragment {
        private Context mContext;
        private String mText;

        public MyFragment(Context context, String text) {
            mContext = context;
            mText = text;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            TextView tv = (TextView) View.inflate(mContext, R.layout.text, null);
            tv.setText(mText);
            return tv;
        }
    }
}
