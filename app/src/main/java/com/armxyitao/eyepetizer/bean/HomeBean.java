package com.armxyitao.eyepetizer.bean;

import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:25
 * @desc 主页最外层的bean
 */
public class HomeBean {
    List<IssueList> issueList;
    String nextPageUrl;
    long nextPublishTime;
    String newestIssueType;

    @Override
    public String toString() {
        return "HomeBean{" +
                "issueList=" + issueList +
                ", nextPageUrl='" + nextPageUrl + '\'' +
                ", nextPublishTime=" + nextPublishTime +
                ", newestIssueType='" + newestIssueType + '\'' +
                '}';
    }

    public List<IssueList> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<IssueList> issueList) {
        this.issueList = issueList;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public String getNewestIssueType() {
        return newestIssueType;
    }

    public void setNewestIssueType(String newestIssueType) {
        this.newestIssueType = newestIssueType;
    }
}
