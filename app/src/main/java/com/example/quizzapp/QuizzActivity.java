package com.example.quizzapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzapp.models.difficulty.Medium;
import com.example.quizzapp.models.quizz.EN_PL_Quizz;
import com.example.quizzapp.models.quizz.Observer;
import com.example.quizzapp.models.quizz.Quizz;

import java.util.List;

public class QuizzActivity extends AppCompatActivity implements Observer {

    private Quizz quizz;

    final AnswerAdapter adapter = new AnswerAdapter();
    private TextView qText;
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
        adapter.setPossibleAnswers(quizz.getCurrentQuestion().getPossibleAnswers());
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
        next = findViewById(R.id.button_next);

        RecyclerView recyclerView = findViewById(R.id.answersRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        quizz = new EN_PL_Quizz(this, new Medium());


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewQuestion();
            }
        });


    }
    private class AnswerHolder extends RecyclerView.ViewHolder{
        String answer;
        private TextView contentTextView;
        public AnswerHolder(LayoutInflater inflater, ViewGroup parent ){
            super(inflater.inflate(R.layout.answer_list_item, parent, false));
            contentTextView= itemView.findViewById(R.id.answer_content);
            View bookItem = itemView.findViewById(R.id.answer_list_item);
            bookItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    checkAnswerCorrectness(answer);
                }
            });
        }
        public void bind(String answer){
            this.answer = answer;
            contentTextView.setText(answer);
        }
    }

    private class AnswerAdapter extends RecyclerView.Adapter<AnswerHolder>{
        private List<String> possibleAnswers;

        @NonNull
        @Override
        public AnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ){
            return new AnswerHolder(getLayoutInflater(), parent);
        }
        @Override
        public void onBindViewHolder(@NonNull AnswerHolder holder, int position){
            if(possibleAnswers!= null){
                String answer = possibleAnswers.get(position);
                holder.bind(answer);
            }
            else{
                Log.d("MainActivity", "No ANSWERS");
            }

        }
        @Override
        public int getItemCount(){

            if(possibleAnswers !=null) {
                return possibleAnswers.size();
            }
            else{
                return 0;
            }
        }

        public void setPossibleAnswers(List<String> possibleAnswers){
            this.possibleAnswers = possibleAnswers;
            notifyDataSetChanged();
        }
    }

}