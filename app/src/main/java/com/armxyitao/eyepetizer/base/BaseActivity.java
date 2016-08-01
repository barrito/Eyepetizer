package com.armxyitao.eyepetizer.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.armxyitao.eyepetizer.network.IModelChangListener;

/**
 * @author 熊亦涛
 * @time 16/8/1  17:29
 * @desc ${TODD}
 */
public class BaseActivity extends AppCompatActivity implements IModelChangListener {
    protected BaseProtocol mProtocol;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handlerMessage(msg);
        }
    };

    public void handlerMessage(Message msg) {
        //空实现
    }
    protected void initProtocol(BaseProtocol protocol) {
        mProtocol = protocol;
        mProtocol.setListener(this);
    }

    @Override
    public void onModelChanged(int action, Object obj) {
        mHandler.obtainMessage(action, obj).sendToTarget();
    }
}
