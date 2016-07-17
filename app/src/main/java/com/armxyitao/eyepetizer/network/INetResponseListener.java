package com.armxyitao.eyepetizer.network;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:33
 * @desc ${TODD}
 */
public interface INetResponseListener {
    void onSuccess(String responseString);

    void onFailure(String error);
}

