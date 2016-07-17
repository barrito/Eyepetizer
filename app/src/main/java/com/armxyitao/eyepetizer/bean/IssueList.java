package com.armxyitao.eyepetizer.bean;

import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/15  22:53
 * @desc ${TODD}
 */
public class IssueList {

    /**
     * releaseTime : 1468512000000
     * type : normal
     * date : 1468512000000
     * publishTime : 1468512000000
     * itemList : []
     * count : 7
     */

    private long releaseTime;
    private String type;
    private long date;
    private long publishTime;
    private int count;
    private List<ItemList> itemList;

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemList> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "IssueList{" +
                "releaseTime=" + releaseTime +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", publishTime=" + publishTime +
                ", count=" + count +
                ", itemList=" + itemList +
                '}';
    }

}
