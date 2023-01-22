package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.quizzapp.database.EnglishWordViewModel;
import com.example.quizzapp.database.PolishWordViewModel;

public class EditWordActivity extends AppCompatActivity {

    public static final String EXTRA_WORD = "pb.edu.pl.EXTRA_WORD";
    public static final String EXTRA_TRANSLATION = "pb.edu.pl.EXTRA_TRANSLATION";
    private EditText editWordText;
    private EditText editTranslationText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int requestCode;
        setContentView(R.layout.activity_edit_word);

        Intent intent = getIntent();
        String wordIn = intent.getStringExtra(CrudActivity.EXTRA_WORD);
        String translationIn = intent.getStringExtra(CrudActivity.EXTRA_TRANSLATION);
        requestCode = intent.getIntExtra(CrudActivity.EXTRA_REQUEST_CODE, 0);


        editWordText = findViewById(R.id.editWordText);
        editTranslationText = findViewById(R.id.editTranslationText);

        editWordText.setText(wordIn);
        editTranslationText.setText(translationIn);


        Button button = findViewById(R.id.editSubmit);
        button.setOnClickListener(view -> {
            Intent replayIntent = new Intent();
            if (TextUtils.isEmpty(editWordText.getText())
                    || TextUtils.isEmpty(editTranslationText.getText())) {
                setResult(RESULT_CANCELED, replayIntent);

            } else {
                String word = editWordText.getText().toString();
                String translation = editTranslationText.getText().toString();
                replayIntent.putExtra(EXTRA_WORD, word);
                replayIntent.putExtra(EXTRA_TRANSLATION, translation);

            }
            intent.putExtra("requestCode", requestCode);
            if (requestCode == 2) setResult(4, replayIntent);
            if (requestCode == 3) setResult(5, replayIntent);
            finish();


        });
    }
}