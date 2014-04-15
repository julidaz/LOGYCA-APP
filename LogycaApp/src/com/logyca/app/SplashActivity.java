package com.logyca.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        //Custom font process
        //TextView myTextView=(TextView)findViewById(id);
        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Museo-300.otf");
        //myTextView.setTypeface(typeFace);
        
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                //Intent i = new Intent(SplashActivity.this, /*Acá va la nueva actividad*/SplashActivity.class);
                //startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
