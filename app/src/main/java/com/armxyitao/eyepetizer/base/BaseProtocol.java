package com.armxyitao.eyepetizer.base;

import com.armxyitao.eyepetizer.network.IModelChangListener;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:58
 * @desc ${TODD}
 */
public class BaseProtocol {
    public IModelChangListener mListener;
    public void setListener(IModelChangListener listener) {
        mListener = listener;
    }

}
