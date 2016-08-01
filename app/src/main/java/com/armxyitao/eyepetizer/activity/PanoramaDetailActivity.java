package com.armxyitao.eyepetizer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.IntentValues;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * @author 熊亦涛
 * @time 16/8/1  19:26
 * @desc ${TODD}
 */
public class PanoramaDetailActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama_detail);
        ArrayList<ItemList> datas = (ArrayList<ItemList>) getIntent().getSerializableExtra(IntentValues.PANORAMA_DATAS);
    }
}
