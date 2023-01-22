package com.example.quizzapp.models.quizz.mode;

import com.example.quizzapp.models.Question;

public interface Mode {
    boolean isQuestionCompleted(Question question);

    boolean hasNext();

    String getEndingText();
}
