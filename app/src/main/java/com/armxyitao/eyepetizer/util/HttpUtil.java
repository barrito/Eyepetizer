package com.armxyitao.eyepetizer.util;

import com.armxyitao.eyepetizer.network.INetResponseListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:30
 * @desc ${TODD}
 */
public class HttpUtil {
    /**
     * get请求
     * @param url
     * @param listener
     */
    public static void doGet(String url, final INetResponseListener listener) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                listener.onFailure(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onSuccess(responseString);
            }
        });
    }
}
