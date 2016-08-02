package com.colg.quanjingdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.player.panoplayer.PanoPlayer;
import com.player.panoplayer.PanoPlayerUrl;
import com.player.renderer.PanoPlayerSurfaceView;

public class MainActivity extends AppCompatActivity {
    PanoPlayerSurfaceView ppsview;
    private PanoPlayer mRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ppsview = (PanoPlayerSurfaceView) findViewById(R.id.ppv);
        mRenderer = new PanoPlayer(ppsview, this);
        ppsview.setRenderer(mRenderer);
        PanoPlayerUrl panoplayerurl = new PanoPlayerUrl();
//        panoplayerurl.SetCubeUrlImage(
//                "http://media.qicdn.detu.com/pano881111469974310974748793/oper/panofile_html_f.jpg",
//                "http://media.qicdn.detu.com/pano881111469974310974748793/oper/panofile_preview_detunew.jpg");
//        "http://v3.cztv.com/cztv/vod/2016/03/15/f71522061dc84e10bc012c5243585e0f/h264_1500k_mp4.mp4"
        panoplayerurl.SetVideoUrlImage("http://v3.cztv.com/cztv/vod/2016/03/15/f71522061dc84e10bc012c5243585e0f/h264_1500k_mp4.mp4","");
//        panoplayerurl.SetVideoUrlImage("http://baobab.wandoujia.com/api/v1/playUrl?vid=8426&editionType=default","");
        mRenderer.Play(panoplayerurl);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRenderer != null) {
            mRenderer.release();
        }
    }
}

