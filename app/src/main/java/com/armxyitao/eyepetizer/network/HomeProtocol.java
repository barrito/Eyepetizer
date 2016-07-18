package com.armxyitao.eyepetizer.network;

import com.armxyitao.eyepetizer.base.BaseProtocol;
import com.armxyitao.eyepetizer.bean.HomeBean;
import com.armxyitao.eyepetizer.constants.NetRequestCons;
import com.google.gson.Gson;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:27
 * @desc ${TODD}
 */
public class HomeProtocol extends BaseProtocol {


    public void getDataFromNet(int action, final String url) {
        deGet(action, url);
    }
    @Override
    public void parseJson(int action, String responseString) {
        Gson gson = new Gson();
        switch (action) {
            case NetRequestCons.GET_HOME_DATA:
                mListener.onModelChanged(NetRequestCons.GET_HOME_DATA_RESULT,gson.fromJson(responseString, HomeBean.class));
                break;
            case NetRequestCons.GET_MORE_HOME_DATA:
                mListener.onModelChanged(NetRequestCons.GET_MORE_HOME_DATA_RESULT,gson.fromJson(responseString, HomeBean.class));
                break;
        }
    }
    @Override
    public void handleFailure(String error) {

    }
}
