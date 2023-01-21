package com.example.quizzapp.models.quizz;

import android.util.Log;

import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.quizz.mode.Mode;

public class Logger implements IQuizz
{
    private IQuizz base;

    public Logger(IQuizz base) { this.base = base; }

    public void setWordsLists()
    {
        Log.d("TESTLOG", "Wywołanie metody setWordsLists");
        base.setWordsLists();
    }

    public void setDifficulty(Difficulty difficulty)
    {
        Log.d("TESTLOG", "Wywołanie metody setDifficulty");
        base.setDifficulty(difficulty);
    }

    public void setMode(Mode mode)
    {
        Log.d("TESTLOG", "Wywołanie metody setMode");
        base.setMode(mode);
    }

    public void setOwner(Observer owner)
    {
        Log.d("TESTLOG", "Wywołanie metody setOwner");
        base.setOwner(owner);
    }

    public void start()
    {
        Log.d("TESTLOG", "Wywołanie metody start");
        base.start();
    }

    public String generateNewQuestion()
    {
        Log.d("TESTLOG", "Wywołanie metody generateNewQuestion");
        return base.generateNewQuestion();
    }

    public boolean isContinuePossible()
    {
        Log.d("TESTLOG", "Wywołanie metody isContinuePossible");
        return base.isContinuePossible();
    }

    public Question getCurrentQuestion()
    {
        Log.d("TESTLOG", "Wywołanie metody getCurrentQuestion");
        return base.getCurrentQuestion();
    }
    public Difficulty getDifficulty(){
        Log.d("TESTLOG", "Wywołanie metody getDifficulty");
        return base.getDifficulty();
    }
}
