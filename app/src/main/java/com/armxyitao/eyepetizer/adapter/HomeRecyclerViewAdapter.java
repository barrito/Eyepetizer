package com.armxyitao.eyepetizer.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.armxyitao.eyepetizer.MyApplication;
import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.activity.HomeDetailActivity;
import com.armxyitao.eyepetizer.activity.WebViewActivity;
import com.armxyitao.eyepetizer.base.RecyclerViewBaseHolder;
import com.armxyitao.eyepetizer.bean.IssueList;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.IntentValues;
import com.armxyitao.eyepetizer.util.ScreenUtil;
import com.armxyitao.eyepetizer.util.TimeUtil;
import com.armxyitao.eyepetizer.util.TypefaceUtil;
import com.armxyitao.eyepetizer.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 熊亦涛
 * @time 16/7/14  19:25
 * @desc ${TODD}
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewBaseHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ItemList> mDatas;
    private final int BANNER = 0;
    private final int TEXTHEADER = 1;
    private final int VIDEO = 2;
    private static final String TYPE_BANNER = "banner1";
    private static final String TYPE_TEXTHEADER = "textHeader";
    private static final String TYPE_VIDEO = "video";
    private Map<Integer, IssueList> mMap = ((MyApplication) Utils.getContext()).mItemMap;
    private int mCurrentPositionInMap = ((MyApplication) Utils.getContext()).mPositionInItemMap;   //当前选中的item在map中key集合的位置
    private int mCurrentPosition;        //当前选中的item的position

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

    public HomeRecyclerViewAdapter(Context context) {
        this(context, null);
    }

    public HomeRecyclerViewAdapter(Context context, List<ItemList> datas) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
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
    public void onBindViewHolder(final RecyclerViewBaseHolder holder, final int position) {
        //第一个item设置marginTop为title的高度
        if (position == 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(ScreenUtil.dip2px(8), ScreenUtil.dip2px(48), ScreenUtil.dip2px(8), 0);
            holder.mItemView.setLayoutParams(layoutParams);
        } else {
            //处理缓存复用,不然复用的时候还是会用position=0的margin值
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(ScreenUtil.dip2px(8), ScreenUtil.dip2px(8), ScreenUtil.dip2px(8), 0);
            holder.mItemView.setLayoutParams(layoutParams);
        }

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
            ((VideoHolder) holder).tv_kind_and_duration.setText("#" + data.getCategory() + "  /  " + TimeUtil.long2String(duration));
            ((VideoHolder) holder).iv_content_bg.setImageURI(Uri.parse(data.getCover().getFeed()));
            if (data.getLabel() != null) {
                ((VideoHolder) holder).tv_label.setText(data.getLabel().getText());
            }
            ((VideoHolder) holder).iv_content_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCurrentPosition = position;
                    handleGoToDetailActivity(v);
                }
            });
            ((VideoHolder) holder).iv_content_bg.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ObjectAnimator.ofFloat(((VideoHolder) holder).tv_container,"alpha",1,0).setDuration(200).start();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            ((VideoHolder) holder).tv_container.setAlpha(1f);
                            break;
                    }
                    return false;
                }
            });
        }
    }

    /**
     * 处理跳转到详情页面
     */
    private void handleGoToDetailActivity(View v) {
        Intent intent = new Intent(mContext, HomeDetailActivity.class);
        Set<Integer> set = mMap.keySet();//这个map封装了count和数据
        List<Integer> list = new ArrayList<>(set);//遍历所有的key
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                ((MyApplication) Utils.getContext()).mPositionInItemMap = i;
                //如果是当前数据的最后一组数据,则不进行下面判断直接跳转
                goToDetailActivity(intent, list, v);
                return;
            }
            if (mCurrentPosition >= list.get(i) && mCurrentPosition < list.get(i + 1)) {
                ((MyApplication) Utils.getContext()).mPositionInItemMap = i;
                goToDetailActivity(intent, list, v);
                return;
            }
        }
    }

    /**
     * 跳转到详情页面
     * mCurrentPosition  当前点击的页面在所以数据里面的位置
     *
     * @param intent
     * @param list   map集合中保存的count
     */
    private void goToDetailActivity(Intent intent, List<Integer> list, View v) {
        IssueList issueList = mMap.get(list.get(((MyApplication) mContext.getApplicationContext()).mPositionInItemMap));
        int position = mCurrentPosition - list.get(((MyApplication) Utils.getContext()).mPositionInItemMap);
        //        List<ItemList> itemList = issueList.getItemList();
        //        Logger.d(itemList.get(position));
        //        for (int i = 0; i < itemList.size(); i++) {
        //            if (!"video".equals(itemList.get(i).getType())) {
        //                itemList.remove(i);
        //                if (!(i == 0 && position == 0)) {
        //                    position--;
        //                }
        //                i--;
        //            }
        //        }
        intent.putExtra(IntentValues.DETAIL_DATAS, issueList);
        intent.putExtra(IntentValues.DETAIL_CURRENT_POSITION, position);
        //        Logger.d("position ="+mCurrentPosition+" realPos ="+list.get(((MyApplication)Utils.getContext()).mPositionInItemMap));
        //        mContext.startActivity(intent);
        Activity activity = (Activity) mContext;

        //        ActivityOptionsCompat options = ActivityOptionsCompat
        //                .makeSceneTransitionAnimation(activity, v, "image");
        //        ActivityCompat.startActivity(activity, intent, options.toBundle());

        mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, v, "image").toBundle());
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
        return mDatas != null ? mDatas.size() : 0;
    }

    /**
     * 封装所有数据的map,key+=每个IssueList的count
     *
     * @param map
     */
    public void addDataMap(Map<Integer, IssueList> map) {
        for (Map.Entry<Integer, IssueList> e : map.entrySet()) {
            Integer key = e.getKey();
            IssueList value = e.getValue();
            mMap.put(key, value);
        }
    }

    /**
     * 设置数据
     *
     * @param datas
     */
    public void setData(List<ItemList> datas) {
        mDatas = datas;
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
        RelativeLayout tv_container;
        TextView tv_content_title;
        TextView tv_kind_and_duration;
        TextView tv_tip;
        TextView tv_label;

        public VideoHolder(View itemView) {
            super(itemView);
            iv_content_bg = (SimpleDraweeView) itemView.findViewById(R.id.iv_content_bg);
            tv_container = (RelativeLayout) itemView.findViewById(R.id.tv_container);
            tv_content_title = (TextView) itemView.findViewById(R.id.tv_content_title);
            tv_kind_and_duration = (TextView) itemView.findViewById(R.id.tv_kind_and_duration);
            tv_tip = (TextView) itemView.findViewById(R.id.tv_tip);
            tv_label = (TextView) itemView.findViewById(R.id.tv_label);
        }
    }

}
