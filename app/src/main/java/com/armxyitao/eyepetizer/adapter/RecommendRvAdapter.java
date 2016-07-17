package com.armxyitao.eyepetizer.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:25
 * @desc ${TODD}
 */
public class RecommendRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ItemList> mDatas;
    private final int BANNER = 0;
    private final int TEXTHEADER = 1;
    private final int VIDEO = 2;
    private final String TYPE_BANNER = "banner1";
    private final String TYPE_TEXTHEADER = "textHeader";
    private final String TYPE_VIDEO = "video";

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


    public RecommendRvAdapter(Context context, List<ItemList> datas) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                return new BannerHolder(mInflater.inflate(R.layout.item_banner, null));
            case TEXTHEADER:
                return new TextHeaderHolder(mInflater.inflate(R.layout.item_texthead, null));
            case VIDEO:
                return new VideoHolder(mInflater.inflate(R.layout.item_video, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemList.ItemData data = mDatas.get(position).getData();
        if (holder instanceof BannerHolder) {
            ((BannerHolder) holder).iv_banner.setImageURI(Uri.parse(data.getImage()));
        } else if (holder instanceof TextHeaderHolder) {
            ((TextHeaderHolder) holder).tv_textHeader.setText(data.getText());
            ((TextHeaderHolder) holder).tv_textHeader.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        } else {
            //设置布局的宽高
            FrameLayout parent = (FrameLayout) ((VideoHolder) holder).iv_content_bg.getParent();
            parent.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));

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

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * Banner
     */
    public class BannerHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView iv_banner;

        public BannerHolder(View itemView) {
            super(itemView);
            iv_banner = (SimpleDraweeView) itemView.findViewById(R.id.iv_banner);
        }
    }

    /**
     * TextHeader
     */
    public class TextHeaderHolder extends RecyclerView.ViewHolder {
        TextView tv_textHeader;

        public TextHeaderHolder(View itemView) {
            super(itemView);
            tv_textHeader = (TextView) itemView.findViewById(R.id.tv_textHeader);
        }
    }

    /**
     * Video
     */
    public class VideoHolder extends RecyclerView.ViewHolder {
        View mItemView;
        SimpleDraweeView iv_content_bg;
        TextView tv_content_title;
        TextView tv_kind_and_duration;
        TextView tv_tip;

        public VideoHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            iv_content_bg = (SimpleDraweeView) itemView.findViewById(R.id.iv_content_bg);
            tv_content_title = (TextView) itemView.findViewById(R.id.tv_content_title);
            tv_kind_and_duration = (TextView) itemView.findViewById(R.id.tv_kind_and_duration);
            tv_tip = (TextView) itemView.findViewById(R.id.tv_tip);
        }
    }
}
