package com.armxyitao.eyepetizer.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.util.TimeUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/8/1  17:52
 * @desc ${TODD}
 */
public class PanoramaAdapter extends RecyclerView.Adapter<PanoramaViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ItemList> mDatas;
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position,List<ItemList> mDatas);
    }

    public PanoramaAdapter(Context context) {
        mContext = context.getApplicationContext();
        mInflater = LayoutInflater.from(mContext);
        mDatas = new ArrayList<>();
    }

    @Override
    public PanoramaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_video, parent, false);
        return new PanoramaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PanoramaViewHolder holder, final int position) {
        ItemList.ItemData data = mDatas.get(position).getData();
        holder.tv_content_title.setText(data.getTitle());

        long duration = data.getDuration();
        holder.tv_kind_and_duration.setText("#" + data.getCategory() + "  /  " + TimeUtil.long2String(duration));
        holder.iv_content_bg.setImageURI(Uri.parse(data.getCover().getFeed()));
        if (data.getLabel() != null) {
            holder.tv_label.setText(data.getLabel().getText());
        }
        holder.iv_content_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null) {
                    mListener.onItemClick(position,mDatas);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void addData(List<ItemList> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(List<ItemList> data) {
        if (mDatas != null) {
            mDatas.clear();
            mDatas.addAll(data);
            notifyDataSetChanged();
        }
    }
}

class PanoramaViewHolder extends RecyclerView.ViewHolder {

    SimpleDraweeView iv_content_bg;
    RelativeLayout tv_container;
    TextView tv_content_title;
    TextView tv_kind_and_duration;
    TextView tv_tip;
    TextView tv_label;
    View itemView;
    public PanoramaViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView.findViewById(R.id.card);
        iv_content_bg = (SimpleDraweeView) itemView.findViewById(R.id.iv_content_bg);
        tv_container = (RelativeLayout) itemView.findViewById(R.id.tv_container);
        tv_content_title = (TextView) itemView.findViewById(R.id.tv_content_title);
        tv_kind_and_duration = (TextView) itemView.findViewById(R.id.tv_kind_and_duration);
        tv_tip = (TextView) itemView.findViewById(R.id.tv_tip);
        tv_label = (TextView) itemView.findViewById(R.id.tv_label);
    }
}
