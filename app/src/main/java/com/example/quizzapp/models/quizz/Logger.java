package com.example.quizzapp.models.quizz;

import android.util.Log;

import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.quizz.mode.Mode;

public class Logger implements IQuizz {
    public static final String TAG = "TESTLOG";
    private final IQuizz base;

    public Logger(IQuizz base) {
        this.base = base;
    }


    public void setWordsLists() {
        Log.d(TAG, "Wywołanie metody setWordsLists");
        base.setWordsLists();
    }

    public void setDifficulty(Difficulty difficulty) {
        Log.d(TAG, "Wywołanie metody setDifficulty");
        base.setDifficulty(difficulty);
    }

    public void setMode(Mode mode) {
        Log.d(TAG, "Wywołanie metody setMode");
        base.setMode(mode);
    }

    public void setOwner(Observer owner) {
        Log.d(TAG, "Wywołanie metody setOwner");
        base.setOwner(owner);
    }

    public void start() {
        Log.d(TAG, "Wywołanie metody start");
        base.start();
    }

    public String generateNewQuestion() {
        Log.d(TAG, "Wywołanie metody generateNewQuestion");
        return base.generateNewQuestion();
    }

    public boolean isContinuePossible() {
        Log.d(TAG, "Wywołanie metody isContinuePossible");
        return base.isContinuePossible();
    }

    public Question getCurrentQuestion() {
        Log.d(TAG, "Wywołanie metody getCurrentQuestion");
        return base.getCurrentQuestion();
    }

    public Difficulty getDifficulty() {
        Log.d(TAG, "Wywołanie metody getDifficulty");
        return base.getDifficulty();
    }
}
