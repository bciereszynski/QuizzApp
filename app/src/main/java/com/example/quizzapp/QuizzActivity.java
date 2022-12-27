package com.example.quizzapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quizzapp.database.EnglishRepository;
import com.example.quizzapp.database.EnglishWord;
import com.example.quizzapp.database.EnglishWordViewModel;
import com.example.quizzapp.database.PolishWord;
import com.example.quizzapp.database.PolishWordViewModel;

import java.util.List;

public class QuizzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        PolishWordViewModel polishWordViewModel;
        EnglishWordViewModel englishWordViewModel;
        TextView qText = findViewById(R.id.question);
        TextView aText = findViewById(R.id.answer);

        polishWordViewModel = new ViewModelProvider(this).get(PolishWordViewModel.class);
        englishWordViewModel = new ViewModelProvider(this).get(EnglishWordViewModel.class);


        LiveData<List<PolishWord>> polishWords = polishWordViewModel.findAll();
        polishWords.observe(this, new Observer<List<PolishWord>>() {
            @Override
            public void onChanged(List<PolishWord> polishWords) {
                if(polishWords.isEmpty()){
                    qText.setText("pusto");
                }
                else {
                    qText.setText(polishWords.get(0).getContent());
                }
            }
        });

    }

}