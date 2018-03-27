package com.gamingdronzz.yts.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamingdronzz.yts.Classes.DummyURLProcessor;
import com.gamingdronzz.yts.Interfaces.IURLProcessor;
import com.gamingdronzz.yts.Listeners.OnURLAvailabilityCheckedListener;
import com.gamingdronzz.yts.R;


public class Splash extends AppCompatActivity {
    ImageView imageView;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.logo);
        info = findViewById(R.id.textview_Info);

        StartAnimations();


    }


    private void StartAnimations() {
        final Animation animationScale = AnimationUtils.loadAnimation(this, R.anim.bounce);



        animationScale.reset();


        imageView.clearAnimation();
        imageView.startAnimation(animationScale);


        animationScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //TODO
                //Change dummy url processor to url processor
                IURLProcessor iurlProcessor = new DummyURLProcessor(new OnURLAvailabilityCheckedListener() {
                    @Override
                    public void OnUrlAvailable() {
                        Log.d("Splash","URl Exists");
                        info.setText("Available");
                        LoadNextActivity();
                    }

                    @Override
                    public void OnUrlNotAvailable() {
                        Log.d("Splash","URl Does not Exists");
                    }
                });

                iurlProcessor.CheckURLAvailability();
                //LoadNextActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void LoadNextActivity() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}
