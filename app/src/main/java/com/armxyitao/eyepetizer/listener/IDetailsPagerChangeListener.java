package com.armxyitao.eyepetizer.listener;

/**
 * @author 熊亦涛
 * @time 16/7/20  19:25
 * @desc ${TODD}
 */
public interface IDetailsPagerChangeListener {
    /**
     * detail页面左右切换时的回调
     * @param position    当先页面在Home页面的位置
     */
    void onDetailsPagerChanged(int position);
}
