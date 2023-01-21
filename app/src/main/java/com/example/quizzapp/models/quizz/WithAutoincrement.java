package com.example.quizzapp.models.quizz;

import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.quizz.mode.Mode;


public class WithAutoincrement implements IQuizz{
    private IQuizz base;
    public WithAutoincrement(IQuizz base){
        this.base = base;
    }
    public void setWordsLists(){
        base.setWordsLists();
    }

    public void setDifficulty(Difficulty difficulty){
        base.setDifficulty(difficulty);
    }

    public void setMode(Mode mode){
        base.setMode(mode);
    }

    public void setOwner(Observer owner){
        base.setOwner(owner);
    }

    public void start(){
        base.start();
    }

    public String generateNewQuestion(){
        return generateNewQuestion();
    }

    public boolean isContinuePossible(){
        return base.isContinuePossible();
    }

    public Question getCurrentQuestion(){
        return base.getCurrentQuestion();
    }
}
