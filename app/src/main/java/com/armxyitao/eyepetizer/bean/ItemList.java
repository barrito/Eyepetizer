package com.armxyitao.eyepetizer.bean;

import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/15  22:55
 * @desc ${TODD}
 */
public class ItemList {
    String type;        //每个item的类型 banner1,textHeader,video
    private ItemData data;


    //封装了内容的web url
    //    webUrl: {
    //        raw: "http://www.wandoujia.com/eyepetizer/detail.html?vid=8084",
    //                forWeibo: "http://wandou.im/2juq48"
    //    }
    public class ItemData {
        String dataType;    //数据类型      Banner/TextHeader/VideoBeanForClient

        String title;       //标题
        String description; //描述

        //type为banner1
        String image;       //type为banner时首页显示的图片的url
        String actionUrl;   //type为banner时 点击跳转的html,需要截取url=后面部分并解码
        //type为textHeader
        String text;        //type为textHeader时显示的文本    "- Weekend Special -"
        String font;        //type为textHeader时的字体 "lobster",

        //type为video
        ItemProvider provider;  //提供者
        String category;    //分类
        ItemAuthor author;  //作者
        ItemCover cover;    //封装了三张图片,分别是首页,详情,背景
        String playUrl;    //视频的播放网址
        long duration;      //视频的时长

        @Override
        public String toString() {
            return "ItemData{" +
                    "dataType='" + dataType + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", image='" + image + '\'' +
                    ", actionUrl='" + actionUrl + '\'' +
                    ", text='" + text + '\'' +
                    ", font='" + font + '\'' +
                    ", provider=" + provider +
                    ", category='" + category + '\'' +
                    ", author=" + author +
                    ", cover=" + cover +
                    ", playUrl='" + playUrl + '\'' +
                    ", duration=" + duration +
                    '}';
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getFont() {
            return font;
        }

        public void setFont(String font) {
            this.font = font;
        }

        public ItemProvider getProvider() {
            return provider;
        }

        public void setProvider(ItemProvider provider) {
            this.provider = provider;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public ItemAuthor getAuthor() {
            return author;
        }

        public void setAuthor(ItemAuthor author) {
            this.author = author;
        }

        public ItemCover getCover() {
            return cover;
        }

        public void setCover(ItemCover cover) {
            this.cover = cover;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }
    }
    /**
     * 视频播放的质量
     */
    List<PlayInfo> playInfo;

    private class PlayInfo {
        int height;//: 360,
        int width;//: 640,
        String name;//: "流畅",
        String type;//: "low",
        String url;



        @Override
        public String toString() {
            return "PlayInfo{" +
                    "height=" + height +
                    ", width=" + width +
                    ", name='" + name + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    /**
     * type=video时 data数据内的provider
     */
    class ItemProvider {
        String name;    //: "乐视",
        String alias;    //: "letv",
        String icon;    //: "http://img.wdjimg.com/mms/icon/v1/6/ca/784da0db524cf8e1448574a764dcdca6_256_256.png"


        @Override
        public String toString() {
            return "ItemProvider{" +
                    "name='" + name + '\'' +
                    ", alias='" + alias + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    /**
     * type=video时 data数据内的author
     */
    class ItemAuthor {
        //        id: 198,
        String icon; //: "http://img.wdjimg.com/image/video/70716e90178d2ea35be9e550c26795f1_0_0.jpeg",
        String name; //: "VICE 中国",
        String description;//: "全球青年文化之声",
        //link: "",
        long latestReleaseTime;//: 1467907200000,
        int videoNum;//: 4,





        @Override
        public String toString() {
            return "ItemAuthor{" +
                    "icon='" + icon + '\'' +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", latestReleaseTime=" + latestReleaseTime +
                    ", videoNum=" + videoNum +
                    '}';
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public long getLatestReleaseTime() {
            return latestReleaseTime;
        }

        public void setLatestReleaseTime(long latestReleaseTime) {
            this.latestReleaseTime = latestReleaseTime;
        }

        public int getVideoNum() {
            return videoNum;
        }

        public void setVideoNum(int videoNum) {
            this.videoNum = videoNum;
        }
    }

    /**
     * type=video时 data数据内的cover
     */
   public class ItemCover {
        String feed;        //: "http://img.wdjimg.com/image/video/f58d66fe20cf45c6b383dedab0ce2b77_0_0.jpeg",
        String detail;      //: "http://img.wdjimg.com/image/video/f58d66fe20cf45c6b383dedab0ce2b77_0_0.jpeg",
        String blurred;     //: "http://img.wdjimg.com/image/video/309b80a2598732c68643d50044a2881c_0_0.jpeg"





        @Override
        public String toString() {
            return "ItemCover{" +
                    "feed='" + feed + '\'' +
                    ", detail='" + detail + '\'' +
                    ", blurred='" + blurred + '\'' +
                    '}';
        }

        public String getFeed() {
            return feed;
        }

        public void setFeed(String feed) {
            this.feed = feed;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getBlurred() {
            return blurred;
        }

        public void setBlurred(String blurred) {
            this.blurred = blurred;
        }
    }
















    @Override
    public String toString() {
        return "ItemList{" +
                "type='" + type + '\'' +
                ", data=" + data +
                ", playInfo=" + playInfo +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemData getData() {
        return data;
    }

    public void setData(ItemData data) {
        this.data = data;
    }

    public List<PlayInfo> getPlayInfo() {
        return playInfo;
    }

    public void setPlayInfo(List<PlayInfo> playInfo) {
        this.playInfo = playInfo;
    }
}
