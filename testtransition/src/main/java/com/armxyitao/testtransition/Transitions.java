package com.armxyitao.testtransition;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;

/**
 * @author 熊亦涛
 * @time 16/7/21  14:45
 * @desc ${TODD}
 */
public class Transitions extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        int flag = getIntent().getExtras().getInt("flag");
        switch (flag) {
            case 0 :
                Explode explode = new Explode();
//                explode.setDuration(2000);
                getWindow().setEnterTransition(explode);
//                    getWindow().setEnterTransition(new Explode());
                break;
            case 1 :
                getWindow().setEnterTransition(new Slide());
                break;
            case 2 :
                Fade fade = new Fade();
//                fade.setDuration(2000);
                getWindow().setEnterTransition(fade);
                getWindow().setExitTransition(fade);
                break;
            case 3 :
//                ChangeClipBounds c = new ChangeClipBounds();
//                c.setDuration(5000);
//                getWindow().setSharedElementEnterTransition(c);
//                getWindow().setSharedElementEnterTransition(new ChangeTransform());
                break;
        }
        setContentView(R.layout.activity_transition_to);
    }
}
