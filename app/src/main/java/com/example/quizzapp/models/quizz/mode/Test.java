package com.example.quizzapp.models.quizz.mode;

import com.example.quizzapp.App;
import com.example.quizzapp.R;
import com.example.quizzapp.models.Question;

public class Test implements Mode {
    private int score = 0;
    private int questionNr = 1;
    private static final int MAX_QUESTION_NUMBER = 5;
    private boolean lastAnswerCorrectness = false;
    public boolean isQuestionCompleted(Question question){
        lastAnswerCorrectness = question.isAnsweredCorrectly();
        return question.isAnswered();
    }

    @Override
    public boolean hasNext() { //first usage after finishing first question
        questionNr++;
        if(lastAnswerCorrectness) score++;
        return questionNr<=MAX_QUESTION_NUMBER;
    }

    @Override
    public String getEndingText() {
        return App.getResource().getQuantityString(R.plurals.scoreMessage, score, score);
    }

    @Override
    public String toString(){
        return App.getResource().getString(R.string.TestMode);
    }
}
