package com.example.quizzapp.models.quizz;

import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.difficulty.Hard;
import com.example.quizzapp.models.difficulty.Medium;
import com.example.quizzapp.models.quizz.mode.Mode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WithAutoincrement implements IQuizz {
    private final IQuizz base;
    private int questionCount = 0;
    private int nextDifficultyId;
    private static final int INCREMENT_CAP = 2;
    private final List<Difficulty> difficulties = new ArrayList<>(Arrays.asList(new Medium(), new Hard()));


    public WithAutoincrement(IQuizz base) {
        this.base = base;
        nextDifficultyId = difficulties.indexOf(this.base.getDifficulty())+1;
    }

    public String generateNewQuestion() {
        questionCount++;
        if (questionCount % (INCREMENT_CAP + 1) == 0 && nextDifficultyId < difficulties.size()) {
            Difficulty newDifficulty = difficulties.get(nextDifficultyId);
            base.setDifficulty(newDifficulty);
            nextDifficultyId = nextDifficultyId + 1;
            questionCount = 0;
        }
        return base.generateNewQuestion();
    }

    public Difficulty getDifficulty() {
        return this.base.getDifficulty();
    }


    public void setWordsLists() {
        base.setWordsLists();
    }

    public void setDifficulty(Difficulty difficulty) {
        base.setDifficulty(difficulty);
    }

    public void setMode(Mode mode) {
        base.setMode(mode);
    }

    public void setOwner(Observer owner) {
        base.setOwner(owner);
    }

    public void start() {
        base.start();
    }


    public boolean isContinuePossible() {
        return base.isContinuePossible();
    }

    public Question getCurrentQuestion() {
        return base.getCurrentQuestion();
    }
}
