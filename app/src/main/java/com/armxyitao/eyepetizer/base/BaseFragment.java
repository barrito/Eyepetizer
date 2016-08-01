package com.armxyitao.eyepetizer.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armxyitao.eyepetizer.activity.HomeActivity;
import com.armxyitao.eyepetizer.network.IModelChangListener;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:57
 * @desc ${TODD}
 */
public class BaseFragment extends Fragment implements IModelChangListener {
    protected HomeActivity mActivity;
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handlerMessage(msg);
        }
    };

    public void handlerMessage(Message msg) {
        //空实现
    }

    protected BaseProtocol mProtocol;

    public BaseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (HomeActivity) getActivity();
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
