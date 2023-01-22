package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddWordActivity extends AppCompatActivity {

    public static final String EXTRA_WORD = "pb.edu.pl.EXTRA_WORD";
    public static final String EXTRA_TRANSLATION = "pb.edu.pl.EXTRA_TRANSLATION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.translationChoice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        EditText addWordText = findViewById(R.id.addWordText);
        EditText addTranslationText = findViewById(R.id.addTranslationText);

        Button button = findViewById(R.id.addWordSubmit);
        button.setOnClickListener(view -> {
            Intent replayIntent = new Intent();
            if (TextUtils.isEmpty(addWordText.getText())
                    || TextUtils.isEmpty(addTranslationText.getText())) {
                setResult(RESULT_CANCELED, replayIntent);

            } else {
                String word = addWordText.getText().toString();
                String traslation = addTranslationText.getText().toString();
                replayIntent.putExtra(EXTRA_WORD, word);
                replayIntent.putExtra(EXTRA_TRANSLATION, traslation);

                if (spinner.getSelectedItemPosition() == 0) {
                    setResult(2, replayIntent);
                }

                if (spinner.getSelectedItemPosition() == 1) {
                    setResult(3, replayIntent);
                }
            }
            finish();
        });
    }


}