package com.armxyitao.eyepetizer.network;

import android.util.Log;

import com.armxyitao.eyepetizer.base.BaseProtocol;
import com.armxyitao.eyepetizer.bean.HomeBean;
import com.armxyitao.eyepetizer.util.HttpUtil;
import com.google.gson.Gson;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:27
 * @desc ${TODD}
 */
public class HomeProtocol extends BaseProtocol {


    public void getDataFromNet(final String url) {
        HttpUtil.doGet(url, new INetResponseListener() {
            @Override
            public void onSuccess(String responseString) {
                Object o = parseJson(responseString);
                Log.d("geduo",o.toString());
                if (mListener != null) {
                    mListener.onModelChanged(o);
                }
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    private Object parseJson(String responseString) {
        Gson gson = new Gson();
        return gson.fromJson(responseString, HomeBean.class);
    }
}
