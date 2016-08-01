package com.armxyitao.eyepetizer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.activity.PanoramaActivity;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.util.ActivityUtil;
import com.armxyitao.eyepetizer.util.ScreenUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/27  22:02
 * @desc ${TODD}
 */
public class FoundRecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<ItemList.ItemData> mDatas;
    private static final String SQUARE_CARD = "SquareCard";
    public static final int SQUARE = 1;
    private static final String RECT_CARD = "RectangleCard";
    public static final int RECT = 2;
    private static final String BANNER_CARD = "Banner";
    public static final int HEAD = 0;
    private List<ItemList.ItemData> mBanners;
    private int bannerCount;

    public void setDatas(List<ItemList.ItemData> datas) {
        mDatas = datas;
        for (int i = 0; i < datas.size(); i++) {
            if ("Banner".equals(datas.get(i).getDataType())) {
                bannerCount++;
                if (mBanners == null) {
                    mBanners = new ArrayList<>();
                }
                mBanners.add(datas.get(i));
            }
        }
    }


    public FoundRecyclerViewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD;
        }
        switch (mDatas.get(position + bannerCount - 1).getDataType()) {
            case SQUARE_CARD:
                return SQUARE;
            case RECT_CARD:
                return RECT;
        }
        return super.getItemViewType(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case SQUARE:
                View square = mInflater.inflate(R.layout.item_found_square, parent, false);
                return new FoundSquareViewHolder(square);
            case RECT:
                View rect = mInflater.inflate(R.layout.item_found_rect, parent, false);
                //                SimpleDraweeView rect = new SimpleDraweeView(mContext);
                return new FoundRectViewHolder(rect);
            case HEAD:
                View banner = mInflater.inflate(R.layout.item_found_banner, parent, false);
                return new FoundHeadViewHolder(banner);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (position == 0) {
            final FoundHeadViewHolder headHolder = (FoundHeadViewHolder) holder;
            headHolder.vp.setAdapter(new FoundBannerAdapter(mContext, mBanners));
            //初始化小圆点
            initDots(headHolder);
            int firstPosition = Integer.MAX_VALUE / 2 / mBanners.size() * mBanners.size();
            headHolder.vp.setCurrentItem(firstPosition);
            headHolder.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    position = position % mBanners.size();
                    for (int i = 0; i < mBanners.size(); i++) {
                        headHolder.dotContainer.getChildAt(i).setSelected(i == position);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
        switch (itemViewType) {
            case SQUARE:
                FoundSquareViewHolder squareHolder = (FoundSquareViewHolder) holder;
                FrameLayout.LayoutParams squareLayoutParams = new FrameLayout.LayoutParams(ScreenUtil.getScreenWidth() / 2, ScreenUtil.getScreenWidth() / 2);
                squareLayoutParams.setMargins(ScreenUtil.dip2px(1), ScreenUtil.dip2px(3), ScreenUtil.dip2px(1), 0);
                squareHolder.square_container.setLayoutParams(squareLayoutParams);
                squareHolder.mIv.setImageURI(Uri.parse(mDatas.get(position + bannerCount - 1).getImage()));
                squareHolder.mTv.setText(mDatas.get(position + bannerCount - 1).getTitle());
                break;
            case RECT:
                FoundRectViewHolder rectHolder = (FoundRectViewHolder) holder;
                FrameLayout.LayoutParams rectLayoutParams = new FrameLayout.LayoutParams(ScreenUtil.getScreenWidth(), ScreenUtil.getScreenWidth() / 2);
                rectLayoutParams.topMargin = ScreenUtil.dip2px(3);
                rectHolder.card.setLayoutParams(rectLayoutParams);
                rectHolder.mIv.setImageURI(Uri.parse(mDatas.get(position + bannerCount - 1).getImage()));
                rectHolder.mIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityUtil.startActivity(mContext,PanoramaActivity.class,false);
                    }
                });
                break;

        }
    }

    /**
     * 初始化小圆点
     *
     * @param holder
     */
    private void initDots(FoundHeadViewHolder holder) {
        for (int i = 0; i < mBanners.size(); i++) {
            ImageView dot = new ImageView(mContext);
            dot.setBackground(mContext.getDrawable(R.drawable.dot_selector));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.leftMargin = ScreenUtil.dip2px(5);
            dot.setLayoutParams(layoutParams);
            holder.dotContainer.addView(dot);
        }
        holder.dotContainer.getChildAt(0).setSelected(true);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size() - bannerCount + 1;
    }


}

class FoundSquareViewHolder extends RecyclerView.ViewHolder {

    public SimpleDraweeView mIv;
    public TextView mTv;
    FrameLayout square_container;

    public FoundSquareViewHolder(View itemView) {
        super(itemView);
        mIv = (SimpleDraweeView) itemView.findViewById(R.id.iv_square);
        mTv = (TextView) itemView.findViewById(R.id.tv);
        square_container = (FrameLayout) itemView.findViewById(R.id.square_container);

    }

}

class FoundRectViewHolder extends RecyclerView.ViewHolder {

    public SimpleDraweeView mIv;
    public CardView card;

    public FoundRectViewHolder(View itemView) {
        super(itemView);

        mIv = (SimpleDraweeView) itemView.findViewById(R.id.iv_rect);
        card = (CardView) itemView.findViewById(R.id.rect_container);
    }
}

class FoundHeadViewHolder extends RecyclerView.ViewHolder {
    ViewPager vp;
    LinearLayout dotContainer;

    public FoundHeadViewHolder(View itemView) {
        super(itemView);
        vp = (ViewPager) itemView.findViewById(R.id.vp_banner);
        dotContainer = (LinearLayout) itemView.findViewById(R.id.dot_container);
    }
}