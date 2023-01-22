package com.example.quizzapp.models.quizz;

import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.quizz.mode.Mode;

public interface IQuizz {
    void setWordsLists();

    Difficulty getDifficulty();

    void setDifficulty(Difficulty difficulty);

    void setMode(Mode mode);

    void setOwner(Observer owner);

    void start();

    String generateNewQuestion();

    boolean isContinuePossible();

    Question getCurrentQuestion();
}
