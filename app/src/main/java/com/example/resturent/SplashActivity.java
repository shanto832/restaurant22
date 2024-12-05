package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView logoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize the logo ImageView
        logoImageView = findViewById(R.id.logoImageView);

        // Load the animation for the logo (up and down)
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Start the animation
        logoImageView.startAnimation(bounce);

        // Delay for 3 seconds, then start the LoginActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the LoginActivity
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();  // Close SplashActivity so the user can't go back to it
            }
        }, 1500);  // 3 seconds delay
    }
}
