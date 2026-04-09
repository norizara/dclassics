package com.example.dclassicsbooks;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {

    private boolean isLoginMode = true;

    // UI elements
    private EditText inputEmail, inputUsername, inputPassword;
    private TextView textForgotPassword, textSwitchMode, authTitle;
    private Button btnPrimaryAction, btnGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // Bind views
        inputEmail = findViewById(R.id.input_email);
        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        textForgotPassword = findViewById(R.id.text_forgot_password);
        textSwitchMode = findViewById(R.id.text_switch_mode);
        authTitle = findViewById(R.id.auth_title);
        btnPrimaryAction = findViewById(R.id.btn_primary_action);
        btnGuest = findViewById(R.id.btn_guest);

        // Entrance animations
        animateEntrance();

        // Set up mode (default: login)
        updateMode();

        // Switch between login and register
        textSwitchMode.setOnClickListener(v -> {
            isLoginMode = !isLoginMode;
            updateMode();
        });

        // Primary action button click
        btnPrimaryAction.setOnClickListener(v -> {
            if (isLoginMode) {
                handleLogin();
            } else {
                handleRegister();
            }
        });

        // Guest button click
        btnGuest.setOnClickListener(v -> {
            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * Updates the UI to reflect login or register mode.
     * Login: username + password
     * Register: email + username + password
     */
    private void updateMode() {
        if (isLoginMode) {
            authTitle.setText(getString(R.string.welcome_title));
            inputEmail.setVisibility(View.GONE);
            setTopMargin(inputUsername, dpToPx(32));
            textForgotPassword.setVisibility(View.VISIBLE);
            btnPrimaryAction.setText(getString(R.string.btn_login));
            textSwitchMode.setText(Html.fromHtml(getString(R.string.switch_to_register), Html.FROM_HTML_MODE_LEGACY));
        } else {
            authTitle.setText("Create Account");
            inputEmail.setVisibility(View.VISIBLE);
            setTopMargin(inputUsername, dpToPx(14));
            textForgotPassword.setVisibility(View.GONE);
            btnPrimaryAction.setText(getString(R.string.btn_register));
            textSwitchMode.setText(Html.fromHtml(getString(R.string.switch_to_login), Html.FROM_HTML_MODE_LEGACY));
        }
    }

    /**
     * Handles login validation.
     */
    private void handleLogin() {
        String username = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (username.isEmpty()) {
            inputUsername.setError("Please enter your username");
            inputUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            inputPassword.setError("Please enter your password");
            inputPassword.requestFocus();
            return;
        }

        // TODO: Implement actual authentication logic here
        Toast.makeText(this, "Signing in...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Handles registration validation.
     */
    private void handleRegister() {
        String email = inputEmail.getText().toString().trim();
        String username = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (email.isEmpty()) {
            inputEmail.setError("Please enter your email");
            inputEmail.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            inputUsername.setError("Please enter a username");
            inputUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            inputPassword.setError("Please enter a password");
            inputPassword.requestFocus();
            return;
        }

        // TODO: Implement actual registration logic here
        Toast.makeText(this, "Creating account...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Applies staggered fade-in + slide-up entrance animations using ViewPropertyAnimator.
     */
    private void animateEntrance() {
        View title = findViewById(R.id.auth_title);
        View subtitle = findViewById(R.id.auth_subtitle);

        animateView(title, 0, 500);
        animateView(subtitle, 100, 500);
        animateView(inputUsername, 200, 500);
        animateView(inputPassword, 300, 500);
        animateView(btnPrimaryAction, 400, 500);
    }

    /**
     * Applies a combined fade-in + slide-up animation using the modern ViewPropertyAnimator API.
     * Unlike the old Animation API, this actually updates the view's real properties.
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

    /**
     * Converts dp to pixels.
     */
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    /**
     * Sets top margin on a view within a LinearLayout.
     */
    private void setTopMargin(View view, int topMarginPx) {
        if (view.getLayoutParams() instanceof android.widget.LinearLayout.LayoutParams) {
            android.widget.LinearLayout.LayoutParams params =
                    (android.widget.LinearLayout.LayoutParams) view.getLayoutParams();
            params.topMargin = topMarginPx;
            view.setLayoutParams(params);
        }
    }
}
