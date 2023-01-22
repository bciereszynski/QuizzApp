package com.example.quizzapp.models.quizz.mode;

import com.example.quizzapp.App;
import com.example.quizzapp.R;
import com.example.quizzapp.models.Question;

public class Learn implements Mode {
    public boolean isQuestionCompleted(Question question) {
        return question.isAnsweredCorrectly();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String getEndingText() {
        return App.getResource().getString(R.string.endOfQuestions);
    }

    @Override
    public String toString() {
        return App.getResource().getString(R.string.LearnMode);
    }
}
