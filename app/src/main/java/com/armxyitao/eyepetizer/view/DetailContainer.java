package com.armxyitao.eyepetizer.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.listener.IDetailContainerListener;

/**
 * @author 熊亦涛
 * @time 16/7/22  15:08
 * @desc ${TODD}
 */
public class DetailContainer extends LinearLayout {
    private final int touchSlop;
    private ObjectAnimator o;
    private int mCurrentPosition;
    private View mRightLoadMore;
    private View mEyeRight;
    private int mRightWidth;
    private int mRightHeight;
    private MarginLayoutParams mRightLayoutParams;
    private boolean goToNext;//是否是去下一页
    private float mStartX;

    /**
     * 手动设置左边的view是哪个布局
     *
     * @param leftLoadMore
     */
    public void setLeftLoadMore(View leftLoadMore) {
        mLeftLoadMore = leftLoadMore;
    }

    /**
     * 设置监听器
     *
     * @param IDetailContainerListener
     */
    public void setIDetailContainerListener(IDetailContainerListener IDetailContainerListener) {
        mListener = IDetailContainerListener;
    }

    private Context mContext;
    private View mLeftLoadMore;
    private int mLeftWidth;
    private ViewPager mViewPager;
    private ViewGroup mContent;
    private int mLeftHeight;
    private int mContentWidth;
    private int mContentHeight;
    private MarginLayoutParams mLeftLayoutParams;
    private boolean ableToPull;
    private float xDown;
    private Scroller scroller;
    private IDetailContainerListener mListener;
    private View mEyeLeft;

    public DetailContainer(Context context) {
        this(context, null);
    }

    public DetailContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        //mLeftLoadMore = LayoutInflater.from(context).inflate(R.layout.view_load_more_pager, null, false);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        scroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mEyeLeft = mLeftLoadMore.findViewById(R.id.eye_inner);
        mEyeRight = mRightLoadMore.findViewById(R.id.eye_inner);

        mLeftWidth = mLeftLoadMore.getMeasuredWidth();
        mLeftHeight = mLeftLoadMore.getMeasuredHeight();

        mRightWidth = mRightLoadMore.getMeasuredWidth();
        mRightHeight = mRightLoadMore.getMeasuredHeight();

        mContentWidth = mContent.getMeasuredWidth();
        mContentHeight = mContent.getMeasuredHeight();
        mViewPager = (ViewPager) mContent.getChildAt(0);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount == 3) {
            mContent = (ViewGroup) getChildAt(1);
            mLeftLoadMore = getChildAt(0);
            mRightLoadMore = getChildAt(2);
        } else if (childCount == 1) {
            //手动添加两个view
            mLeftLoadMore = LayoutInflater.from(mContext).inflate(R.layout.view_load_more_pager, null, false);
            mLeftLoadMore.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            addView(mLeftLoadMore, 0);

            mRightLoadMore = LayoutInflater.from(mContext).inflate(R.layout.view_load_more_pager, null, false);
            mRightLoadMore.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            addView(mRightLoadMore, 2);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        //        Log.e("geduo", "onInterceptTouchEvent  ");
        setIsAbleToPull();
        if (ableToPull) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    xDown = event.getRawX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float xMove = event.getRawX();
                    int distance = (int) (xMove - xDown);
                    //如果往左移且当前是第一个viewpager||往右移且当前是最后一个viewpager就不拦截
                    if(distance<0&&goToNext) {
//                        Logger.d("最后一张 我要拦截");
                        return true;
                    }
                    if(distance>0&&!goToNext) {
//                        Logger.d("第一张 我要拦截");
                        return true;
                    }
//                    Logger.d("我没有拦截");

                case MotionEvent.ACTION_UP:

            }
        }
        return false;
    }
    /**
     * 判断是否可以拉动
     */
    private void setIsAbleToPull() {
        int currentItem = mViewPager.getCurrentItem();
        goToNext = currentItem == mViewPager.getAdapter().getCount() - 1;
        ableToPull = currentItem == 0 || currentItem == mViewPager.getAdapter().getCount() - 1;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getRawX();
                if (o != null && o.isRunning()) {
                    o.cancel();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) event.getX();
                //x轴移动的距离,相反的...
                int moveX = (int) (xDown - endX);
                //处理越界
                //移动到的X轴,负数
                int scrollX = getScrollX();
                if (!goToNext) {
                    //移动到的值加上即将要移的值,如果超过范围就移动到指定位置
                    if (scrollX + moveX <= -mLeftWidth) {
                        scrollTo(-mLeftWidth, 0);
                    } else if (scrollX + moveX >= 0) {
                        scrollTo(0, 0);
                    } else {
                        scrollBy(moveX, 0);
                        mEyeLeft.setRotation(xDown * 2 * 360 / mLeftWidth);
                    }
                } else {
                    //右边不可能越界  就不判断了
                    scrollBy(moveX, 0);
                    mEyeRight.setRotation(xDown * 2 * 360 / mRightWidth);
                }

                xDown = endX;
                break;
            case MotionEvent.ACTION_UP:
                int x = getScrollX();
                if (!goToNext) {
                    if (Math.abs(x) >= mLeftWidth / 2) {
                        // 展示Menu
                        //                    scrollTo(-mLeftWidth, 0);
                        showMenu(-mLeftWidth, mEyeLeft);
                        if (mListener != null) {
                            mListener.onFingerUp(false);
                        }
                    } else {
                        // 隐藏Menu
                        //                    scrollTo(0, 0);
                        hideMenu();
                    }
                } else {
                    if (Math.abs(x) >= mRightWidth / 2) {
                        showMenu(mRightWidth, mEyeRight);
                        if (mListener != null) {
                            mListener.onFingerUp(true);
                        }
                    } else {
                        hideMenu();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * 展示Menu,通过scroller展示,实现动画效果
     *
     * @param endX         移动到的位置
     * @param animatorView 需要添加旋转动画的View
     */
    private void showMenu(int endX, View animatorView) {
        //1.起始的x  2.起始的y  3.x的偏移量  4.y的偏移量  5.移动的时间
        o = ObjectAnimator.ofFloat(animatorView, "rotation", animatorView.getRotation(), animatorView.getRotation() + 360 * 10);
        o.setDuration(1000 * 10);
        o.setRepeatCount(ValueAnimator.INFINITE);
        o.setRepeatMode(ValueAnimator.RESTART);
        o.start();
        scroller.startScroll(getScrollX(), 0, endX - getScrollX(), 0, 1000);
        //一定要调用刷新方法
        invalidate();
    }
    /**
     * 隐藏Menu,通过scroller,实现动画效果
     */
    private void hideMenu() {
        scroller.startScroll(getScrollX(), 0, -getScrollX(), 0, 1000);
        invalidate();
    }
    /**
     * scroller需要配合computeScroll使用,计算每次需要移动的距离
     * 计算每次滑动的距离
     */
    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            //一定要调用刷新方法
            invalidate();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //        Log.e("geduo", "dispatchTouchEvent  ");
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLeftLayoutParams = (MarginLayoutParams) mLeftLoadMore.getLayoutParams();
        mRightLayoutParams = (MarginLayoutParams) mRightLoadMore.getLayoutParams();
        //layout...
        mLeftLoadMore.layout(-mLeftWidth, 0, 0, mLeftHeight);
        mContent.layout(0, 0, mContentWidth, mContentHeight);
        mRightLoadMore.layout(mRightWidth, 0, 2 * mRightWidth, mRightHeight);
    }

    /**
     * 重置UI布局
     */
    public void resetLayout() {
        if (!scroller.isFinished()) {
            scroller.forceFinished(true);
        }
        scrollTo(0, 0);

    }



    public void setCurrentPosition(int currentPosition) {
        mCurrentPosition = currentPosition;
    }
}

