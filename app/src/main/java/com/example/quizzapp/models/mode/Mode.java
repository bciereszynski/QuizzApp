package com.example.quizzapp.models.mode;

import com.example.quizzapp.models.Question;

public interface Mode {
    boolean isQuestionCompleted(Question question);
    boolean hasNext();
    String getEndingText();
}
