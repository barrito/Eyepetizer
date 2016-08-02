package com.armxyitao.eyepetizer.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;

import com.armxyitao.eyepetizer.R;
import com.armxyitao.eyepetizer.constants.IntentValues;
import com.orhanobut.logger.Logger;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.widget.Settings;
import tv.danmaku.ijk.media.widget.media.AndroidMediaController;
import tv.danmaku.ijk.media.widget.media.IjkVideoView;

/**
 * @author 熊亦涛
 * @time 16/7/30  23:49
 * @desc ${TODD}
 */
public class VideoActivity extends Activity {
    private IjkVideoView videoView;
    private Settings mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video);
        String url = getIntent().getStringExtra(IntentValues.VIDEO_PLAY_URL);
        Logger.d(url);
        mSettings = new Settings(this);
        videoView = (IjkVideoView) findViewById(R.id.videoview);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        AndroidMediaController controller = new AndroidMediaController(this, false);
        videoView.setMediaController(controller);
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView!=null&&videoView.isPlaying()) {
            videoView.release(true);
        }
    }
}
