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

    public int requestCode;
    public static final String EXTRA_WORD = "pb.edu.pl.EXTRA_WORD";
    public static final String EXTRA_TRANSLATION = "pb.edu.pl.EXTRA_TRANSLATION";
    public PolishWordViewModel polishWordViewModel;
    public EnglishWordViewModel englishWordViewModel;
    private EditText editWordText,editTranslationText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word);

        polishWordViewModel = new ViewModelProvider(this).get(PolishWordViewModel.class);
        englishWordViewModel = new ViewModelProvider(this).get(EnglishWordViewModel.class);

        Intent intent = getIntent();
        String wordIn = intent.getStringExtra(CrudActivity.EXTRA_WORD);
        String translationIn = intent.getStringExtra(CrudActivity.EXTRA_TRANSLATION);


        editWordText = findViewById(R.id.editWordText);
        editTranslationText = findViewById(R.id.editTranslationText);

        editWordText.setText(wordIn);
        editTranslationText.setText(translationIn);



        Button button = findViewById(R.id.editSubmit);
        button.setOnClickListener(view -> {
            Intent replayIntent = new Intent();
            if(TextUtils.isEmpty(editWordText.getText())
            || TextUtils.isEmpty(editTranslationText.getText())){
                setResult(RESULT_CANCELED,replayIntent);

            }else{
                String word = editWordText.getText().toString();
                String traslation = editTranslationText.getText().toString();
                replayIntent.putExtra(EXTRA_WORD,word);
                replayIntent.putExtra(EXTRA_TRANSLATION,traslation);

            }
            intent.putExtra("requestCode", requestCode);
            if(this.requestCode==1)setResult(4,replayIntent);
            if(this.requestCode==2)setResult(5,replayIntent);
            finish();



        });
    }
}