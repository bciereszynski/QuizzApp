package com.example.quizzapp.models.quizz;

import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.quizz.mode.Mode;


public class QuizzDecorator implements IQuizz {
    private final IQuizz wrappee;


    public QuizzDecorator(IQuizz wrappee) {
        this.wrappee = wrappee;
    }

    public String generateNewQuestion() {
        return wrappee.generateNewQuestion();
    }

    public Difficulty getDifficulty() {
        return this.wrappee.getDifficulty();
    }


    public void setWordsLists() {
        wrappee.setWordsLists();
    }

    public void setDifficulty(Difficulty difficulty) {
        wrappee.setDifficulty(difficulty);
    }

    public void setMode(Mode mode) {
        wrappee.setMode(mode);
    }

    public void setOwner(Observer owner) {
        wrappee.setOwner(owner);
    }

    public void start() {
        wrappee.start();
    }


    public boolean isContinuePossible() {
        return wrappee.isContinuePossible();
    }

    public Question getCurrentQuestion() {
        return wrappee.getCurrentQuestion();
    }
}
