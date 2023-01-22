package com.example.quizzapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.quizz.IQuizz;

import java.util.ArrayList;
import java.util.List;

public class FragmentQuizz extends Fragment implements Observer {

    private IQuizz quizz;

    final AnswerAdapter adapter = new AnswerAdapter();
    private int selectedItem;
    TextView instruction;
    private TextView questionText;
    private Button next;

    public void setQuizz(IQuizz quizz) {
        this.quizz = quizz;
    }

    private void setNewQuestion() {
        String result = quizz.generateNewQuestion();

        if (result.equals("OK")) {
            questionText.setText(quizz.getCurrentQuestion().getGoodAnswer().getContent());
            adapter.setPossibleAnswers(quizz.getCurrentQuestion().getPossibleAnswers());
        } else {
            instruction.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
            questionText.setText(result);
            questionText.setTextSize(30);
            questionText.setOnClickListener(v -> getActivity().finish());
            adapter.setPossibleAnswers(new ArrayList<>());
        }
        next.setEnabled(false);
        selectedItem = -1;
    }

    private void checkAnswerCorrectness(String userAnswer) {
        quizz.getCurrentQuestion().tryAnswer(userAnswer);
        next.setEnabled(quizz.isContinuePossible());
    }

    @Override
    public void update() {
        setNewQuestion();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quizz, container, false);
        //binding
        instruction = view.findViewById(R.id.translate_instructions);
        questionText = view.findViewById(R.id.question);
        next = view.findViewById(R.id.button_next);

        next.setEnabled(false);

        RecyclerView recyclerView = view.findViewById(R.id.answersRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        quizz.setOwner(this);
        quizz.start();

        next.setOnClickListener(v -> setNewQuestion());
        return view;
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