package com.armxyitao.eyepetizer.bean;

import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/12  19:21
 * @desc ${TODD}
 */
public class Splashbean {

    /**
     * forceOff : true
     * videoNum : 5
     * version : 8
     * offset : 18000000
     */

    private AutoCacheBean autoCache;
    /**
     * displayTimeDuration : 3
     * imageUrl : http://img.wdjimg.com/image/video/64306021bd26b579ec925f671f9e67ca_0_0.jpeg
     * countdown : true
     * actionUrl :
     * blurredImageUrl : http://img.wdjimg.com/image/video/59f8071cb7aa5cbea6619504f2478e66_0_0.jpeg
     * startTime : 1466697600000
     * canSkip : true
     * endTime : 1466870340000
     * version : 39
     * adTrack : []
     */

    private StartPageAdBean startPageAd;
    /**
     * imageUrl : http://img.wdjimg.com/image/video/5c28075c01656ade6e4d28c6f2ff8b80_0_0.jpeg
     * actionUrl :
     * version : 68
     */

    private StartPageBean startPage;
    /**
     * startTime : 9
     * endTime : 22
     * message : 今天的日报已准备好，请享用！
     * version : 18
     */

    private PushBean push;

    public AutoCacheBean getAutoCache() {
        return autoCache;
    }

    public void setAutoCache(AutoCacheBean autoCache) {
        this.autoCache = autoCache;
    }

    public StartPageAdBean getStartPageAd() {
        return startPageAd;
    }

    public void setStartPageAd(StartPageAdBean startPageAd) {
        this.startPageAd = startPageAd;
    }

    public StartPageBean getStartPage() {
        return startPage;
    }

    public void setStartPage(StartPageBean startPage) {
        this.startPage = startPage;
    }

    public PushBean getPush() {
        return push;
    }

    public void setPush(PushBean push) {
        this.push = push;
    }

    public static class AutoCacheBean {
        private boolean forceOff;
        private int videoNum;
        private String version;
        private int offset;

        public boolean isForceOff() {
            return forceOff;
        }

        public void setForceOff(boolean forceOff) {
            this.forceOff = forceOff;
        }

        public int getVideoNum() {
            return videoNum;
        }

        public void setVideoNum(int videoNum) {
            this.videoNum = videoNum;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }
    }

    public static class StartPageAdBean {
        private int displayTimeDuration;
        private String imageUrl;
        private boolean countdown;
        private String actionUrl;
        private String blurredImageUrl;
        private long startTime;
        private boolean canSkip;
        private long endTime;
        private String version;
        private List<?> adTrack;

        public int getDisplayTimeDuration() {
            return displayTimeDuration;
        }

        public void setDisplayTimeDuration(int displayTimeDuration) {
            this.displayTimeDuration = displayTimeDuration;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public boolean isCountdown() {
            return countdown;
        }

        public void setCountdown(boolean countdown) {
            this.countdown = countdown;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public String getBlurredImageUrl() {
            return blurredImageUrl;
        }

        public void setBlurredImageUrl(String blurredImageUrl) {
            this.blurredImageUrl = blurredImageUrl;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public boolean isCanSkip() {
            return canSkip;
        }

        public void setCanSkip(boolean canSkip) {
            this.canSkip = canSkip;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public List<?> getAdTrack() {
            return adTrack;
        }

        public void setAdTrack(List<?> adTrack) {
            this.adTrack = adTrack;
        }
    }

    public static class StartPageBean {
        private String imageUrl;
        private String actionUrl;
        private String version;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class PushBean {
        private int startTime;
        private int endTime;
        private String message;
        private String version;

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
