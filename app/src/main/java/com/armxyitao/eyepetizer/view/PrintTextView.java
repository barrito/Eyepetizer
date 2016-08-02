package com.armxyitao.eyepetizer.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.SoundPool;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.armxyitao.eyepetizer.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author barrito
 * @time 16/7/19  20:14
 * @desc 打字效果TextView
 */

public class PrintTextView extends TextView {
    private String mShowTextString = null;
    private Timer mPrintTimer = null;
    private Context mContext;
    private OnPrintListener mOnPrintListener = null;
    private SoundPool mPool;
    private static final int PRINT_DELAY = 5;
    private int mPrintDelay = PRINT_DELAY;
    private int mSoundId = -1;
    private int mAudioSrc;  //音频资源id
    final boolean[] isLoadComplete = {false};

    public interface OnPrintListener {
        void onStartPrint();

        void onEndPrint();
    }

    public void setOnPrintListener(OnPrintListener onPrintListener) {
        mOnPrintListener = onPrintListener;
    }

    public void setAudioSrc(int audioSrc) {
        mAudioSrc = audioSrc;
    }

    public PrintTextView(Context context) {
        this(context, null);
    }

    public PrintTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.PrintTextView);
        //获取设置的音效资源
        mAudioSrc = t.getResourceId(R.styleable.PrintTextView_audio_src, 0);
        t.recycle();
        init();
    }

    public void start(final String textString) {
        this.start(textString, PRINT_DELAY);
    }

    public void start(final String textString, final int printDelay) {
        this.start(textString, printDelay, false);
    }

    public void start(final String textString, final int printDelay, final boolean audioEnable) {
        if (TextUtils.isEmpty(textString) || printDelay < 0) {
            return;
        }
        //加载音频资源
        if (mAudioSrc != 0 && mSoundId == -1) {
            mSoundId = mPool.load(mContext, mAudioSrc, 1);
        }
        mPool.autoPause();
        postPrintTask(textString, printDelay, audioEnable);

    }

    /**
     * 开启打印任务
     *
     * @param textString  需要打印的文本
     * @param printDelay  打印间隔
     * @param audioEnable 是否开启音效
     */
    private void postPrintTask(final String textString, final int printDelay, final boolean audioEnable) {
        post(new Runnable() {
            @Override
            public void run() {
                mShowTextString = textString;
                mPrintDelay = printDelay;
                setText("");
                startPrintTask(audioEnable);
                if (mOnPrintListener != null) {
                    mOnPrintListener.onStartPrint();
                }
            }
        });
    }

    /**
     * 停止音乐--->提供给外界
     */
    public void stop() {
        stopPrintTask();
        mPool.autoPause();
        mPool.release();
        mSoundId = -1;
    }

    /**
     * 初始化
     */
    private void init() {
        mPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        if (mAudioSrc != 0) {
            mSoundId = mPool.load(mContext, mAudioSrc, 1);
        }
    }

    /**
     * 开启任务
     *
     * @param audioEnable 是否开启音效
     */
    private void startPrintTask(boolean audioEnable) {
        stopPrintTask();
        mPrintTimer = new Timer();
        mPrintTimer.schedule(new PrintTask(audioEnable), mPrintDelay);
    }

    /**
     * 关闭任务
     */
    private void stopPrintTask() {
        if (mPrintTimer != null) {
            mPrintTimer.cancel();
            mPrintTimer = null;
        }
    }

    /**
     * 播放音效
     */
    private void playAudio() {
        mPool.play(mSoundId, 0.3f, 0.3f, 0, 0, 1);
    }

    /**
     * 任务详情
     */
    class PrintTask extends TimerTask {
        private boolean mAudioEnable;

        public PrintTask(boolean audioEnable) {
            mAudioEnable = audioEnable;
        }

        @Override
        public void run() {
            post(new Runnable() {
                @Override
                public void run() {
                    if (getText().toString().length() < mShowTextString.length()) {
                        if (mAudioEnable) {
                            if (!isLoadComplete[0]) {
                                mPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                                    @Override
                                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                                        isLoadComplete[0] = true;//加载完成
                                        mPool.setOnLoadCompleteListener(null);
                                    }
                                });
                            }
                        }
                        //是否开启音效
                        if (mAudioEnable && mAudioSrc != 0) {
                            playAudio();
                        }
                        setText(mShowTextString.substring(0, getText().toString().length() + 1));

                        startPrintTask(mAudioEnable);
                    } else {
                        //立即停止
                        mPool.autoPause();
                        stopPrintTask();
                        if (null != mOnPrintListener) {
                            mOnPrintListener.onEndPrint();
                        }
                    }
                }
            });
        }
    }

}
