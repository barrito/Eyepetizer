package com.armxyitao.eyepetizer.bean;

import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:51
 * @desc ${TODD}
 */
public class ItemListBean {
    private String type;
    /**
     * dataType : VideoBeanForClient
     * id : 8096
     * title : 「突击风暴 2」最新预告
     * description : 「突击风暴 2」最新预告来袭。游戏由韩国游戏开发商 GameHi 公司开发，「突击风暴 2」作为续作， 在保持了前作打击感和快节奏的同时，由虚幻 3 引擎打造的画面也会带给玩家新的体验。From Squeeze Studio Animation
     * provider : {"name":"Vimeo","alias":"vimeo","icon":"http://img.wdjimg.com/image/video/c3ad630be461cbb081649c9e21d6cbe3_256_256.png"}
     * category : 动画
     * author : null
     * cover : {"feed":"http://img.wdjimg.com/image/video/1b224319f30ac3caeb08d69f9904e0ff_0_0.jpeg","detail":"http://img.wdjimg.com/image/video/1b224319f30ac3caeb08d69f9904e0ff_0_0.jpeg","blurred":"http://img.wdjimg.com/image/video/6e71144e883ad66d76febc52bffe5b79_0_0.jpeg","sharing":null}
     * playUrl : http://baobab.wandoujia.com/api/v1/playUrl?vid=8096&editionType=default
     * duration : 120
     * webUrl : {"raw":"http://www.wandoujia.com/eyepetizer/detail.html?vid=8096","forWeibo":"http://wandou.im/2k5g8u"}
     * releaseTime : 1468425600000
     * playInfo : [{"height":360,"width":640,"name":"流畅","type":"low","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=8096&editionType=low"},{"height":480,"width":854,"name":"标清","type":"normal","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=8096&editionType=normal"},{"height":720,"width":1280,"name":"高清","type":"high","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=8096&editionType=high"}]
     * consumption : {"collectionCount":612,"shareCount":1166,"replyCount":90}
     * campaign : null
     * waterMarks : null
     * adTrack : null
     * tags : [{"id":30,"name":"游戏","actionUrl":"eyepetizer://tag/30/?title=%E6%B8%B8%E6%88%8F","adTrack":null},{"id":22,"name":"预告","actionUrl":"eyepetizer://tag/22/?title=%E9%A2%84%E5%91%8A","adTrack":null},{"id":544,"name":"美女","actionUrl":"eyepetizer://tag/544/?title=%E7%BE%8E%E5%A5%B3","adTrack":null},{"id":184,"name":"动作","actionUrl":"eyepetizer://tag/184/?title=%E5%8A%A8%E4%BD%9C","adTrack":null},{"id":560,"name":"性感","actionUrl":"eyepetizer://tag/560/?title=%E6%80%A7%E6%84%9F","adTrack":null}]
     * type : NORMAL
     * idx : 0
     * shareAdTrack : null
     * favoriteAdTrack : null
     * webAdTrack : null
     * date : 1468425600000
     * promotion : null
     * label : null
     */

    private DataBean data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String dataType;
        private int id;
        private String title;
        private String description;
        /**
         * name : Vimeo
         * alias : vimeo
         * icon : http://img.wdjimg.com/image/video/c3ad630be461cbb081649c9e21d6cbe3_256_256.png
         */

        private ProviderBean provider;
        private String category;
        private Object author;
        /**
         * feed : http://img.wdjimg.com/image/video/1b224319f30ac3caeb08d69f9904e0ff_0_0.jpeg
         * detail : http://img.wdjimg.com/image/video/1b224319f30ac3caeb08d69f9904e0ff_0_0.jpeg
         * blurred : http://img.wdjimg.com/image/video/6e71144e883ad66d76febc52bffe5b79_0_0.jpeg
         * sharing : null
         */

        private CoverBean cover;
        private String playUrl;
        private int duration;
        /**
         * raw : http://www.wandoujia.com/eyepetizer/detail.html?vid=8096
         * forWeibo : http://wandou.im/2k5g8u
         */

        private WebUrlBean webUrl;
        private long releaseTime;
        /**
         * collectionCount : 612
         * shareCount : 1166
         * replyCount : 90
         */

        private ConsumptionBean consumption;
        private Object campaign;
        private Object waterMarks;
        private Object adTrack;
        private String type;
        private int idx;
        private Object shareAdTrack;
        private Object favoriteAdTrack;
        private Object webAdTrack;
        private long date;
        private Object promotion;
        private Object label;
        /**
         * height : 360
         * width : 640
         * name : 流畅
         * type : low
         * url : http://baobab.wandoujia.com/api/v1/playUrl?vid=8096&editionType=low
         */

        private List<PlayInfoBean> playInfo;
        /**
         * id : 30
         * name : 游戏
         * actionUrl : eyepetizer://tag/30/?title=%E6%B8%B8%E6%88%8F
         * adTrack : null
         */

        private List<TagsBean> tags;

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public ProviderBean getProvider() {
            return provider;
        }

        public void setProvider(ProviderBean provider) {
            this.provider = provider;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Object getAuthor() {
            return author;
        }

        public void setAuthor(Object author) {
            this.author = author;
        }

        public CoverBean getCover() {
            return cover;
        }

        public void setCover(CoverBean cover) {
            this.cover = cover;
        }

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public WebUrlBean getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(WebUrlBean webUrl) {
            this.webUrl = webUrl;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public ConsumptionBean getConsumption() {
            return consumption;
        }

        public void setConsumption(ConsumptionBean consumption) {
            this.consumption = consumption;
        }

        public Object getCampaign() {
            return campaign;
        }

        public void setCampaign(Object campaign) {
            this.campaign = campaign;
        }

        public Object getWaterMarks() {
            return waterMarks;
        }

        public void setWaterMarks(Object waterMarks) {
            this.waterMarks = waterMarks;
        }

        public Object getAdTrack() {
            return adTrack;
        }

        public void setAdTrack(Object adTrack) {
            this.adTrack = adTrack;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public Object getShareAdTrack() {
            return shareAdTrack;
        }

        public void setShareAdTrack(Object shareAdTrack) {
            this.shareAdTrack = shareAdTrack;
        }

        public Object getFavoriteAdTrack() {
            return favoriteAdTrack;
        }

        public void setFavoriteAdTrack(Object favoriteAdTrack) {
            this.favoriteAdTrack = favoriteAdTrack;
        }

        public Object getWebAdTrack() {
            return webAdTrack;
        }

        public void setWebAdTrack(Object webAdTrack) {
            this.webAdTrack = webAdTrack;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public Object getPromotion() {
            return promotion;
        }

        public void setPromotion(Object promotion) {
            this.promotion = promotion;
        }

        public Object getLabel() {
            return label;
        }

        public void setLabel(Object label) {
            this.label = label;
        }

        public List<PlayInfoBean> getPlayInfo() {
            return playInfo;
        }

        public void setPlayInfo(List<PlayInfoBean> playInfo) {
            this.playInfo = playInfo;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class ProviderBean {
            private String name;
            private String alias;
            private String icon;

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

        public static class CoverBean {
            private String feed;
            private String detail;
            private String blurred;
            private Object sharing;

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

            public Object getSharing() {
                return sharing;
            }

            public void setSharing(Object sharing) {
                this.sharing = sharing;
            }
        }

        public static class WebUrlBean {
            private String raw;
            private String forWeibo;

            public String getRaw() {
                return raw;
            }

            public void setRaw(String raw) {
                this.raw = raw;
            }

            public String getForWeibo() {
                return forWeibo;
            }

            public void setForWeibo(String forWeibo) {
                this.forWeibo = forWeibo;
            }
        }

        public static class ConsumptionBean {
            private int collectionCount;
            private int shareCount;
            private int replyCount;

            public int getCollectionCount() {
                return collectionCount;
            }

            public void setCollectionCount(int collectionCount) {
                this.collectionCount = collectionCount;
            }

            public int getShareCount() {
                return shareCount;
            }

            public void setShareCount(int shareCount) {
                this.shareCount = shareCount;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }
        }

        public static class PlayInfoBean {
            private int height;
            private int width;
            private String name;
            private String type;
            private String url;

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

        public static class TagsBean {
            private int id;
            private String name;
            private String actionUrl;
            private Object adTrack;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getActionUrl() {
                return actionUrl;
            }

            public void setActionUrl(String actionUrl) {
                this.actionUrl = actionUrl;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public void setAdTrack(Object adTrack) {
                this.adTrack = adTrack;
            }
        }
    }
}
