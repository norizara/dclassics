package com.example.dclassicsbooks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Animate the logo, app name, and tagline
        View logo = findViewById(R.id.splash_logo);
        View appName = findViewById(R.id.splash_app_name);
        View tagline = findViewById(R.id.splash_tagline);

        animateView(logo, 0, 600);
        animateView(appName, 200, 600);
        animateView(tagline, 400, 600);

        // Navigate to AuthActivity after delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }

    /**
     * Applies a combined fade-in + slide-up animation using ViewPropertyAnimator.
     */
    private void animateView(View view, int startDelay, int duration) {
        view.setAlpha(0f);
        view.setTranslationY(60f);
        view.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(duration)
                .setStartDelay(startDelay)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }
}
