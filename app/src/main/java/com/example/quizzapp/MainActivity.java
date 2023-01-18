package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonTests = findViewById(R.id.buttonTests);

        buttonTests.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
        Button buttonCurd = findViewById(R.id.buttonCrud);
        buttonCurd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CrudActivity.class);
            startActivity(intent);
        });



    }
}
