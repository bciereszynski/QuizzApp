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

import com.example.quizzapp.models.difficulty.Hard;
import com.example.quizzapp.models.quizz.EN_PL_Quizz;
import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.quizz.Quizz;

import java.util.List;

public class QuizzActivity extends AppCompatActivity implements Observer {

    private Quizz quizz;

    final AnswerAdapter adapter = new AnswerAdapter();
    private int selectedItem;
    private TextView qText;
    private Button next;
    private boolean checkAnswerCorrectness(String userAnswer){
        int resultMessageId = 0;
        boolean result = quizz.getCurrentQuestion().TryAnswer(userAnswer);
        if(quizz.isContinuePossible())
            next.setEnabled(true);
        else{
            next.setEnabled(false);
        }
        if(result){
            resultMessageId = R.string.correct_answer;
            Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            return false;
        }

    }
    private void setNewQuestion(){
        quizz.generateNewQuestion();
        qText.setText(quizz.getCurrentQuestion().getGoodAnswer().getContent());
        adapter.setPossibleAnswers(quizz.getCurrentQuestion().getPossibleAnswers());
        selectedItem = -1;
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

        next.setEnabled(false);

        RecyclerView recyclerView = findViewById(R.id.answersRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        quizz = new EN_PL_Quizz(this, new Hard());


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
        private Button answerButton;
        public AnswerHolder(LayoutInflater inflater, ViewGroup parent ){
            super(inflater.inflate(R.layout.answer_list_item, parent, false));
            contentTextView= itemView.findViewById(R.id.answer_content);
            answerButton= itemView.findViewById(R.id.answer_content);
            answerButton.setOnClickListener((v)->{
                int position = getBindingAdapterPosition();
                if (position == RecyclerView.NO_POSITION || position == selectedItem) {
                    return;
                }
                checkAnswerCorrectness(answer);
                answerButton.setEnabled(false);

                int previousSelected = selectedItem;
                selectedItem = position;// Check item.

                if (previousSelected != -1) {
                    adapter.notifyItemChanged(previousSelected);// Deselected the previous item.
                }
                adapter.notifyItemChanged(selectedItem);// Select the current item.
            });
        }
        public void bind(String answer, boolean choosen){
            this.answer = answer;
            contentTextView.setText(answer);
            if(choosen)
                answerButton.setEnabled(false);
            else
                answerButton.setEnabled(true);
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
                Boolean choosen = position == selectedItem;
                holder.bind(answer, choosen);
            }
            else{
                Log.d("MainActivity", "Answers ERROR");
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