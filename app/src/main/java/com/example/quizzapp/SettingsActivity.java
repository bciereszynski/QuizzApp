package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Spinner spinnerMode = findViewById(R.id.spinnerSettingsMode);
        ArrayAdapter<CharSequence> adaptermode = ArrayAdapter.createFromResource(this,R.array.modeChoice, android.R.layout.simple_spinner_item);
        adaptermode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMode.setAdapter(adaptermode);
        spinnerMode.setOnItemSelectedListener(this);

        Spinner spinnerPLEN = findViewById(R.id.spinnerSettingsPLEN);
        ArrayAdapter<CharSequence> adapterPLEN = ArrayAdapter.createFromResource(this,R.array.translationChoice, android.R.layout.simple_spinner_item);
        adapterPLEN.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPLEN.setAdapter(adapterPLEN);
        spinnerPLEN.setOnItemSelectedListener(this);


        Spinner spinnerDifficulty = findViewById(R.id.spinnerSettingsDifficluty);
        ArrayAdapter<CharSequence> adapterDifficluty = ArrayAdapter.createFromResource(this,R.array.difficultyChoice, android.R.layout.simple_spinner_item);
        adapterDifficluty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficluty);
        spinnerDifficulty.setOnItemSelectedListener(this);

        Button submitButton = findViewById(R.id.buttonSettings);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, QuizzActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}