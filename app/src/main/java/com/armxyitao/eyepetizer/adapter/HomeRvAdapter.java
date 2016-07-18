package com.armxyitao.eyepetizer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.activity.HomeActivity;
import com.armxyitao.eyepetizer.activity.WebViewActivity;
import com.armxyitao.eyepetizer.base.RecyclerViewBaseHolder;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.util.ScreenUtil;
import com.armxyitao.eyepetizer.util.TypefaceUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:25
 * @desc ${TODD}
 */
public class HomeRvAdapter extends RecyclerView.Adapter<RecyclerViewBaseHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ItemList> mDatas;
    private final int BANNER = 0;
    private final int TEXTHEADER = 1;
    private final int VIDEO = 2;
    private final String TYPE_BANNER = "banner1";
    private final String TYPE_TEXTHEADER = "textHeader";
    private final String TYPE_VIDEO = "video";
    private final int mHeight;

    /**
     * 三种类型的view
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (mDatas.get(position).getType()) {
            case TYPE_BANNER:
                return BANNER;
            case TYPE_TEXTHEADER:
                return TEXTHEADER;
            case TYPE_VIDEO:
                return VIDEO;
        }
        return super.getItemViewType(position);
    }


    public HomeRvAdapter(Context context, List<ItemList> datas) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
        mHeight = ((HomeActivity) context).mHeight;
    }

    @Override
    public RecyclerViewBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerHolder(mInflater.inflate(R.layout.item_banner, parent, false));
            case TEXTHEADER:
                return new TextHeaderHolder(mInflater.inflate(R.layout.item_texthead, parent, false));
            case VIDEO:
                return new VideoHolder(mInflater.inflate(R.layout.item_video, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewBaseHolder holder, int position) {
        //第一个item设置marginTop为title的高度
        if(position==0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(ScreenUtil.dip2px(3),ScreenUtil.dip2px(48),ScreenUtil.dip2px(3),0);
            holder.mItemView.setLayoutParams(layoutParams);
        }
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"hello",Toast.LENGTH_SHORT).show();
            }
        });

        final ItemList.ItemData data = mDatas.get(position).getData();
        if (holder instanceof BannerHolder) {
            //设置src
            ((BannerHolder) holder).iv_banner.setImageURI(Uri.parse(data.getImage()));
            //设置layoutParams
            //            LinearLayout parent = (LinearLayout) ((BannerHolder) holder).iv_banner.getParent();
            //            parent.setLayoutParams(new LinearLayoutCompat.LayoutParams(-1, ScreenUtil.dip2px(200)));
            //点击事件
            ((BannerHolder) holder).iv_banner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("url", getRealUrl(data.getActionUrl()));
                    mContext.startActivity(intent);
                }
            });
        } else if (holder instanceof TextHeaderHolder) {
            ((TextHeaderHolder) holder).tv_textHeader.setText(data.getText());
            TypefaceUtil.setTypeface(((TextHeaderHolder) holder).tv_textHeader);
            //            FrameLayout parent = (FrameLayout) ((TextHeaderHolder) holder).tv_textHeader.getParent();
            //            parent.setLayoutParams(new FrameLayout.LayoutParams(-1, ScreenUtil.dip2px(50)));
        } else {
            //设置布局的宽高
            //            FrameLayout parent = (FrameLayout) ((VideoHolder) holder).iv_content_bg.getParent();
            //            parent.setLayoutParams(new FrameLayout.LayoutParams(-1,ScreenUtil.dip2px(240)));

            ((VideoHolder) holder).tv_content_title.setText(data.getTitle());

            long duration = data.getDuration();
            String realDuration;
            if (duration % 60 == 0) {
                realDuration = duration / 60 + "′ 00'″";
            } else {
                realDuration = duration / 60 + "′ " + duration % 60 + "'″";
            }
            ((VideoHolder) holder).tv_kind_and_duration.setText("#" + data.getCategory() + "  /  " + realDuration);
            ((VideoHolder) holder).iv_content_bg.setImageURI(Uri.parse(data.getCover().getFeed()));
            if (data.getLabel() != null) {
                ((VideoHolder) holder).tv_label.setText(data.getLabel().getText());
            }

        }
    }

    /**
     * 截取url并解码
     *
     * @param url
     * @return
     */
    private String getRealUrl(String url) {
        //"eyepetizer://webview/?title=%E4%B8%8D%E8%A6%81%E5%81%9A%E4%BD%8E%E5%A4%B4%E6%97%8F&url=http%3A%2F%2Fwww.wandoujia.com%2Feyepetizer%2Fcollection.html%3Fname%3Dphubbing%26shareable%3Dtrue",
        url = url.substring(url.lastIndexOf("url=") + 4, url.length());
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * Banner
     */
    public class BannerHolder extends RecyclerViewBaseHolder {
        SimpleDraweeView iv_banner;

        public BannerHolder(View itemView) {
            super(itemView);
            iv_banner = (SimpleDraweeView) itemView.findViewById(R.id.iv_banner);
        }
    }

    /**
     * TextHeader
     */
    public class TextHeaderHolder extends RecyclerViewBaseHolder {
        TextView tv_textHeader;
        public TextHeaderHolder(View itemView) {
            super(itemView);
            tv_textHeader = (TextView) itemView.findViewById(R.id.tv_textHeader);
        }
    }

    /**
     * Video
     */
    public class VideoHolder extends RecyclerViewBaseHolder {
        SimpleDraweeView iv_content_bg;
        TextView tv_content_title;
        TextView tv_kind_and_duration;
        TextView tv_tip;
        TextView tv_label;

        public VideoHolder(View itemView) {
            super(itemView);
            iv_content_bg = (SimpleDraweeView) itemView.findViewById(R.id.iv_content_bg);
            tv_content_title = (TextView) itemView.findViewById(R.id.tv_content_title);
            tv_kind_and_duration = (TextView) itemView.findViewById(R.id.tv_kind_and_duration);
            tv_tip = (TextView) itemView.findViewById(R.id.tv_tip);
            tv_label = (TextView) itemView.findViewById(R.id.tv_label);
        }
    }

}
