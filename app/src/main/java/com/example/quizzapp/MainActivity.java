package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView helloText = findViewById(R.id.buttonTests);
        helloText.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizzActivity.class);
            startActivity(intent);
        });
    }
}
