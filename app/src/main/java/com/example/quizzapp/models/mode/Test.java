package com.example.quizzapp.models.mode;

import com.example.quizzapp.models.Question;

public class Test implements Mode {
    private int score = 0;
    private boolean lastAnswerCorrectness = false;
    public boolean isQuestionCompleted(Question question){
        lastAnswerCorrectness = question.isAnsweredCorrectly();
        return question.isAnswered();
    }

    @Override
    public boolean hasNext(int questionNumber) {
        int maxQuestionNumber = 5;
        if(lastAnswerCorrectness) score++;
        return questionNumber<=maxQuestionNumber;
    }

    @Override
    public String getEndingText() {
        return "Twój wynik: " + score + "punkty";
    }
}
