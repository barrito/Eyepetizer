package com.armxyitao.eyepetizer.bean;

import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/8/1  17:48
 * @desc 全景bean
 */
public class PanoramaInfo {
    public List<ItemList> itemList;
    public int count;
    public int total;
    public String nextPageUrl;

    @Override
    public String toString() {
        return "PanoramaInfo{" +
                "itemList=" + itemList +
                ", count=" + count +
                ", total=" + total +
                ", nextPageUrl='" + nextPageUrl + '\'' +
                '}';
    }
}
