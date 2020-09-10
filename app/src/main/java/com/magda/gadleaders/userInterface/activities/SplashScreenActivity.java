package com.magda.gadleaders.userInterface.activities;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.magda.gadleaders.R;

import static com.magda.gadleaders.utils.Constants.SPLASH_SCREEN;

public class SplashScreenActivity extends AppCompatActivity {
    private ImageView mLogo;
    private Animation sideAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        sideAnim= AnimationUtils.loadAnimation(this, R.anim.side_anim );

        mLogo = findViewById(R.id.ivSplashLogo);

        mLogo.setAnimation(sideAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent = new Intent(SplashScreenActivity.this,HomeScreenActivity.class);
               startActivity(intent);
               finish();
            }
        },SPLASH_SCREEN);
    }
}