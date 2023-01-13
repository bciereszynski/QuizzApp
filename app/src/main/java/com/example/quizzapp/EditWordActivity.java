package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class EditWordActivity extends AppCompatActivity {

    public static final String EXTRA_EDIT_WORD = "pb.edu.pl.EDIT_WORD";
    public static final String EXTRA_EDIT_TRANSLATION = "pb.edu.pl.EDIT_TRANSLATION";
    private EditText editWordText,editTranslationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word);

        editWordText = findViewById(R.id.editWordText);
        editTranslationText = findViewById(R.id.editTranslationText);

        Button button = findViewById(R.id.editSubmit);
        button.setOnClickListener(view -> {
            Intent replayIntent = new Intent();
            if(TextUtils.isEmpty(editWordText.getText())||TextUtils.isEmpty(editTranslationText.getText())){
                setResult(RESULT_CANCELED,replayIntent);
            }else {
                String word = editWordText.getText().toString();
                replayIntent.putExtra(EXTRA_EDIT_WORD, word);
                String translation = editTranslationText.getText().toString();
                replayIntent.putExtra(EXTRA_EDIT_TRANSLATION,translation);
                setResult(RESULT_OK,replayIntent);
            }
            finish();
        });
    }
}