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
    private List<Word> polish = new LinkedList<Word>();;
    private List<Word> english = new LinkedList<Word>();;
    private void checkAnswerCorrectness(String userAnswer){
        int resultMessageId = 0;
        if(quizz.getCurrentQuestuion().TryAnswer(userAnswer)){
            resultMessageId = R.string.correct_answer;
            Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
        }
    }
    private TextView qText;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private Button next;

    private void setNewQuestion(){
        quizz.generateNewQuestion();
        qText.setText(quizz.getCurrentQuestuion().getGoodAnswer().getContent());
        answer1.setText(quizz.getCurrentQuestuion().getPossibleAnswers().get(0));
        answer2.setText(quizz.getCurrentQuestuion().getPossibleAnswers().get(1));
        answer3.setText(quizz.getCurrentQuestuion().getPossibleAnswers().get(2));
        answer4.setText(quizz.getCurrentQuestuion().getPossibleAnswers().get(3));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        PolishWordViewModel polishWordViewModel;
        EnglishWordViewModel englishWordViewModel;

        //binding
        qText = findViewById(R.id.question);
        answer1 = findViewById(R.id.button1);
        answer2 = findViewById(R.id.button2);
        answer3 = findViewById(R.id.button3);
        answer4 = findViewById(R.id.button4);
        next = findViewById(R.id.button_next);

        polishWordViewModel = new ViewModelProvider(this).get(PolishWordViewModel.class);
        englishWordViewModel = new ViewModelProvider(this).get(EnglishWordViewModel.class);

        quizz = new Quizz(polish,english);


        englishWordViewModel.findAll().observe(this, englishWords -> {
            for (Word w : englishWords) {
                english.add(w);
            }
            quizz.setAnswerWords(english);
            polishWordViewModel.findAll().observe(this, polishWords -> {
                for (Word w : polishWords) {
                    polish.add(w);
                }
                quizz.setTestedWords(polish);
                setNewQuestion();
            });

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewQuestion();
            }
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
        answer3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(answer3.getText().toString());
            }
        });
        answer4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswerCorrectness(answer4.getText().toString());
            }
        });


    }

}