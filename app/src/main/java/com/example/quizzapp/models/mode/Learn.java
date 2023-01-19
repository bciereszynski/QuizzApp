package com.example.quizzapp.models.mode;

import com.example.quizzapp.models.Question;

public class Learn implements Mode {
    public boolean isQuestionCompleted(Question question){
        return question.isAnsweredCorrectly();
    }

    @Override
    public boolean hasNext(int questionNumber) {
        return true;
    }

    @Override
    public String getEndingText() {
        return "Przejrzałeś już wszystkie pytania!";
    }
}
