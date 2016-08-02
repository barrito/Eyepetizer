package com.armxyitao.eyepetizer.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.activity.VideoActivity;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.IntentValues;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author 熊亦涛
 * @time 16/7/18  21:54
 * @desc ${TODD}
 */
public class DetailFragment extends Fragment {

    private View mParent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mParent = inflater.inflate(R.layout.fragment_detail, null);
        ItemList itemList = (ItemList) getArguments().getSerializable("ItemList");
        initView(itemList);
        return mParent;
    }

    public void initView(final ItemList itemList) {
        if (itemList != null && itemList.getData() != null) {
            SimpleDraweeView showIv = (SimpleDraweeView) mParent.findViewById(R.id.iv_show);
            showIv.setImageURI(Uri.parse(itemList.getData().getCover().getDetail()));
            final String text = itemList.getData().getLabel().getText();
            showIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    if (TextUtils.isEmpty(text)) {
                        intent.setClass(getActivity().getApplicationContext(), VideoActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(IntentValues.VIDEO_PLAY_URL, itemList.getData().getPlayUrl());
                    } else {
//                        intent.setClass(getActivity().getApplicationContext(), PanoramaVideoActivity.class);
                    }
                    startActivity(intent);
                }
            });
            SimpleDraweeView bottomIv = (SimpleDraweeView) mParent.findViewById(R.id.iv_bottom);
            bottomIv.setImageURI(Uri.parse(itemList.getData().getCover().getBlurred()));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //        EventBus.getDefault().unregister(this);
    }

    //    @Subscribe(threadMode = ThreadMode.MAIN)
    //    public void refreshUI(RefreshFragmentEvent event) {
    //        initView(event.itemList);
    //    }

}
