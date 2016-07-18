package com.armxyitao.eyepetizer.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author 熊亦涛
 * @time 16/7/18  15:55
 * @desc ${TODD}
 */
public class RecyclerViewBaseHolder extends RecyclerView.ViewHolder{
    public View mItemView;
    public RecyclerViewBaseHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
    }
}