package com.armxyitao.testtransition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        Fade fade = new Fade();
//        fade.setDuration(2000);
//        getWindow().setEnterTransition(fade);
//        getWindow().setExitTransition(fade);
        setContentView(R.layout.activity_main);
    }

    public void explode(View view) {
        intent = new Intent(this,Transitions.class);
        intent.putExtra("flag",0);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void slide(View view) {
        intent = new Intent(this,Transitions.class);
        intent.putExtra("flag",1);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void fade(View view) {
        intent = new Intent(this,Transitions.class);
        intent.putExtra("flag",2);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void share(View view) {

        View fab = findViewById(R.id.fab_button);
        intent = new Intent(this,Transitions.class);
        intent.putExtra("flag",3);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation
                (this,view,"share").toBundle());
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation
                (this, Pair.create(view,"share"),Pair.create(fab,"fab")).toBundle());
    }
}
