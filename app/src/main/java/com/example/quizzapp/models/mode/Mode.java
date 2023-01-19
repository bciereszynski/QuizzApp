package com.example.quizzapp.models.mode;

import com.example.quizzapp.models.Question;

public interface Mode {
    boolean isContinuePossible(Question question);

}
