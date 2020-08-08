package com.shashankassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView tvTitle = findViewById(R.id.tv_title);
        AppCompatButton btnSignUp = findViewById(R.id.btn_signup);

        tvTitle.setText(getString(R.string.signup));

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, FeedActivity.class);
            startActivity(intent);
        });
    }
}
