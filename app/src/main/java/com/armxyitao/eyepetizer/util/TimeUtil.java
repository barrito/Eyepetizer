package com.armxyitao.eyepetizer.util;

/**
 * @author 熊亦涛
 * @time 16/7/19  21:22
 * @desc ${TODD}
 */
public class TimeUtil {
    /**
     * 毫秒转分秒--->05′18″
     *
     * @param duration
     * @return
     */
    public static String long2String(long duration) {
        long min = duration / 60;
        long sec = duration % 60;
        String minStr;
        String secStr;
        minStr = min >= 10 ? min + "" : "0" + min;
        secStr = sec >= 10 ? sec + "" : "0" + sec;
        return sec == 0 ? minStr + "′ 00'″" : minStr + "′ " + secStr + "'″";
    }
}
