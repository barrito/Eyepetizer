package com.armxyitao.eyepetizer.event;

import com.armxyitao.eyepetizer.bean.ItemList;

/**
 * @author 熊亦涛
 * @time 16/7/24  21:54
 * @desc ${TODD}
 */
public class RefreshFragmentEvent {
    public ItemList itemList;
    public RefreshFragmentEvent(ItemList itemList) {
        this.itemList = itemList;
    }
}
