package com.armxyitao.eyepetizer.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.armxyitao.eyepetizer.bean.ItemList;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/29  14:10
 * @desc ${TODD}
 */
public class FoundBannerAdapter extends PagerAdapter {
    private Context mContext;
    private List<ItemList.ItemData> mDatas;

    public FoundBannerAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<ItemList.ItemData> data) {
        mDatas = data;
    }

    public FoundBannerAdapter(Context context, List<ItemList.ItemData> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position%mDatas.size();
        ItemList.ItemData itemData = mDatas.get(position);
        String image = itemData.getImage();
        SimpleDraweeView iv = new SimpleDraweeView(mContext);
        //        iv.setLayoutParams(new ViewGroup.LayoutParams(-1,-1));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setImageURI(Uri.parse(getRealUrl(image)));
        container.addView(iv);
        return iv;
    }

    private String getRealUrl(String url) {
        //"eyepetizer://webview/?title=%E4%B8%8D%E8%A6%81%E5%81%9A%E4%BD%8E%E5%A4%B4%E6%97%8F&url=http%3A%2F%2Fwww.wandoujia.com%2Feyepetizer%2Fcollection.html%3Fname%3Dphubbing%26shareable%3Dtrue",
        //        url = url.substring(url.lastIndexOf("url=") + 4, url.length());
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
