package com.armxyitao.eyepetizer.base;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:57
 * @desc ${TODD}
 */
public class BaseFragment extends Fragment {
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handlerMessage(msg);
        }
    };

    public void handlerMessage(Message msg) {
        //空实现
    }
}
