package com.example.quizzapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.database.EnglishRepository;
import com.example.quizzapp.database.EnglishWord;
import com.example.quizzapp.database.EnglishWordViewModel;
import com.example.quizzapp.database.PolishWord;
import com.example.quizzapp.database.PolishWordViewModel;
import com.example.quizzapp.database.Word;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.Quizz;

import java.util.LinkedList;
import java.util.List;

public class QuizzActivity extends AppCompatActivity {

    private Quizz quizz;
    private List<Word> polish;
    private List<Word> english;
    private void checkAnswerCorrectness(String userAnswer){
        int resultMessageId = 0;
        if(quizz.getCurrentQuestuion().TryAnswer(0)){
            resultMessageId = R.string.correct_answer;
            Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        PolishWordViewModel polishWordViewModel;
        EnglishWordViewModel englishWordViewModel;
        TextView qText = findViewById(R.id.question);

        Button answer1 = findViewById(R.id.button1);
        Button answer2 = findViewById(R.id.button2);
        Button answer3 = findViewById(R.id.button3);
        Button answer4 = findViewById(R.id.button4);


        polishWordViewModel = new ViewModelProvider(this).get(PolishWordViewModel.class);
        englishWordViewModel = new ViewModelProvider(this).get(EnglishWordViewModel.class);

        quizz = new Quizz();

        englishWordViewModel.findAll().observe(this, englishWords -> {
            List<Word> ewords = new LinkedList<>();
            for (Word w : englishWords) {
                ewords.add(w);
            }
            english = ewords;
            polishWordViewModel.findAll().observe(this, polishWords -> {
                List<Word> pwords = new LinkedList<>();
                for (Word w : polishWords) {
                    pwords.add(w);
                }
                polish = pwords;

                quizz.generateNewQuestion();
                qText.setText(quizz.getCurrentQuestuion().getGoodAnswer().getContent());
                answer1.setText(quizz.getCurrentQuestuion().getPossibleAnswers().get(1).getContent());

            });
        });




        answer1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               checkAnswerCorrectness(answer1.getText().toString());
            }
        });
        answer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(answer2.getText().toString());
            }
        });


    }

}