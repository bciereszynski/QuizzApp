package com.example.quizzapp.models.mode;

import com.example.quizzapp.models.Question;

public class Test implements Mode {
    public boolean isContinuePossible(Question question){

        return question.isAnswered();
    }
}
