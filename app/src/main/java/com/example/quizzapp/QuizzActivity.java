package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.models.quizz.Observer;
import com.example.quizzapp.models.quizz.PL_EN_Quizz;
import com.example.quizzapp.models.quizz.Quizz;

public class QuizzActivity extends AppCompatActivity implements Observer {

    private Quizz quizz;


    private TextView qText;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private Button next;
    private void checkAnswerCorrectness(String userAnswer){
        int resultMessageId = 0;
        if(quizz.getCurrentQuestion().TryAnswer(userAnswer)){
            resultMessageId = R.string.correct_answer;
            Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
        }
    }
    private void setNewQuestion(){
        quizz.generateNewQuestion();
        qText.setText(quizz.getCurrentQuestion().getGoodAnswer().getContent());
        answer1.setText(quizz.getCurrentQuestion().getPossibleAnswers().get(0));
        answer2.setText(quizz.getCurrentQuestion().getPossibleAnswers().get(1));
        answer3.setText(quizz.getCurrentQuestion().getPossibleAnswers().get(2));
        answer4.setText(quizz.getCurrentQuestion().getPossibleAnswers().get(3));
    }

    @Override
    public void update() {
        setNewQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);


        //binding
        qText = findViewById(R.id.question);
        answer1 = findViewById(R.id.button1);
        answer2 = findViewById(R.id.button2);
        answer3 = findViewById(R.id.button3);
        answer4 = findViewById(R.id.button4);
        next = findViewById(R.id.button_next);


        quizz = new PL_EN_Quizz(this);


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