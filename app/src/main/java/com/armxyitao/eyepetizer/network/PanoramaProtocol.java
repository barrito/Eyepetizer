package com.armxyitao.eyepetizer.network;

import com.armxyitao.eyepetizer.base.BaseProtocol;
import com.armxyitao.eyepetizer.bean.PanoramaInfo;
import com.armxyitao.eyepetizer.constants.NetRequestCons;
import com.google.gson.Gson;

/**
 * @author 熊亦涛
 * @time 16/8/1  17:35
 * @desc ${TODD}
 */
public class PanoramaProtocol extends BaseProtocol {
    @Override
    public void handleFailure(String error) {

    }

    @Override
    public void handleJson(int action, String responseString) {
        Gson gson = new Gson();
        switch (action) {
            case NetRequestCons.GET_PANORAMA_BY_TIME :
                mListener.onModelChanged(NetRequestCons.GET_PANORAMA_BY_TIME_RESULT
                        ,gson.fromJson(responseString,
                                PanoramaInfo.class));
                break;
            case NetRequestCons.GET_PANORAMA_BY_SHARE :
                mListener.onModelChanged(NetRequestCons.GET_PANORAMA_BY_SHARE_RESULT
                        ,gson.fromJson(responseString,
                                PanoramaInfo.class));
                break;
        }
    }
}
