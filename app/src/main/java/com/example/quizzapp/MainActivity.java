package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonTests = findViewById(R.id.buttonTests);

        buttonTests.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizzActivity.class);
            startActivity(intent);
        });
        Button buttonCurd = findViewById(R.id.buttonCrud);
        buttonCurd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CrudActivity.class);
            startActivity(intent);
        });



    }
}
