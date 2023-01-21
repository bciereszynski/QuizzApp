package com.example.quizzapp.models.quizz;

import android.util.Log;

import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.quizz.mode.Mode;

public class Logger implements IQuizz
{
    private IQuizz base;
    // new Logger(new Quizz());


    public Logger(IQuizz base) { this.base = base; }

    public void setWordsLists()
    {
        Log.d("QUIZZAPP", "Wywołanie metody setWordsLists");
        base.setWordsLists();
    }

    public void setDifficulty(Difficulty difficulty)
    {
        Log.d("QUIZZAPP", "Wywołanie metody setDifficulty");
        base.setDifficulty(difficulty);
    }

    public void setMode(Mode mode)
    {
        Log.d("QUIZZAPP", "Wywołanie metody setMode");
        base.setMode(mode);
    }

    public void setOwner(Observer owner)
    {
        Log.d("QUIZZAPP", "Wywołanie metody setOwner");
        base.setOwner(owner);
    }

    public void start()
    {
        Log.d("QUIZZAPP", "Wywołanie metody start");
        base.start();
    }

    public String generateNewQuestion()
    {
        Log.d("QUIZZAPP", "Wywołanie metody generateNewQuestion");
        return base.generateNewQuestion();
    }

    public boolean isContinuePossible()
    {
        Log.d("QUIZZAPP", "Wywołanie metody isContinuePossible");
        return base.isContinuePossible();
    }

    public Question getCurrentQuestion()
    {
        Log.d("QUIZZAPP", "Wywołanie metody getCurrentQuestion");
        return base.getCurrentQuestion();
    }
    public Difficulty getDifficulty(){
        Log.d("QUIZZAPP", "Wywołanie metody getDifficulty");
        return base.getDifficulty();
    }
}
