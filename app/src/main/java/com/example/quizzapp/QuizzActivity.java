package com.example.quizzapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzapp.models.difficulty.Hard;
import com.example.quizzapp.models.mode.Test;
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

    private void setNewQuestion() {
        quizz.generateNewQuestion();
        qText.setText(quizz.getCurrentQuestion().getGoodAnswer().getContent());
        adapter.setPossibleAnswers(quizz.getCurrentQuestion().getPossibleAnswers());
        selectedItem = -1;
    }

    private void checkAnswerCorrectness(String userAnswer) {
        boolean result = quizz.getCurrentQuestion().TryAnswer(userAnswer);
        next.setEnabled(quizz.isContinuePossible());

        if (result) {
            Toast.makeText(this,  R.string.correct_answer, Toast.LENGTH_SHORT).show();
        }

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

        quizz = new EN_PL_Quizz(this, new Hard(), new Test());

        next.setOnClickListener(v -> setNewQuestion());

    }

    private class AnswerHolder extends RecyclerView.ViewHolder {

        String answer;
        private final Button answerButton;

        public AnswerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.answer_list_item, parent, false));
            answerButton = itemView.findViewById(R.id.answer_content);
            answerButton.setOnClickListener(v -> {
                checkAnswerCorrectness(answer);
                int previousSelected = selectedItem;
                selectedItem = getBindingAdapterPosition();// Check item.

                if (previousSelected != -1) {
                    adapter.notifyItemChanged(previousSelected);// Deselected the previous item.
                }
                adapter.notifyItemChanged(selectedItem);// Select the current item.
            });
        }

        public void bind(String answer, boolean chosen) {
            this.answer = answer;
            answerButton.setText(answer);
            answerButton.setEnabled(!chosen);
        }

    }

    private class AnswerAdapter extends RecyclerView.Adapter<AnswerHolder> {
        private List<String> possibleAnswers;

        @NonNull
        @Override
        public AnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AnswerHolder(getLayoutInflater(), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull AnswerHolder holder, int position) {
            if (possibleAnswers != null) {
                String answer = possibleAnswers.get(position);
                boolean chosen = position == selectedItem;
                holder.bind(answer, chosen);
            } else {
                Log.d("MainActivity", "Answers ERROR");
            }

        }

        @Override
        public int getItemCount() {

            if (possibleAnswers != null) {
                return possibleAnswers.size();
            } else {
                return 0;
            }
        }

        public void setPossibleAnswers(List<String> possibleAnswers) {
            this.possibleAnswers = possibleAnswers;
            notifyDataSetChanged();
        }
    }

}