package com.armxyitao.eyepetizer.base;

import com.armxyitao.eyepetizer.network.IModelChangListener;
import com.armxyitao.eyepetizer.network.INetResponseListener;
import com.armxyitao.eyepetizer.util.HttpUtil;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:58
 * @desc ${TODD}
 */
public abstract class BaseProtocol {
    public IModelChangListener mListener;

    public void setListener(IModelChangListener listener) {
        mListener = listener;
    }

    public void getDataFromNet(int action, final String url) {
        deGet(action, url);
    }

    public void deGet(final int action, String url) {
        HttpUtil.doGet(url, new INetResponseListener() {
            @Override
            public void onSuccess(String responseString) {
              handleJson(action,responseString);
            }

            @Override
            public void onFailure(String error) {
                handleFailure(error);
            }
        });
    }

    /**
     * 请求失败
     * @param error
     */
    public abstract void handleFailure(String error);


    /**
     * 请求成功的回调
     * @param responseString
     */
    public abstract void handleJson(int action, String responseString);

}
