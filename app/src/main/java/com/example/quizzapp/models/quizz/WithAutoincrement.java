package com.example.quizzapp.models.quizz;

import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.difficulty.Easy;
import com.example.quizzapp.models.difficulty.Hard;
import com.example.quizzapp.models.difficulty.Medium;
import com.example.quizzapp.models.difficulty.VeryEasy;
import com.example.quizzapp.models.quizz.mode.Mode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WithAutoincrement extends QuizzDecorator {
    private int questionCount = 0;
    private int nextDifficultyId;
    private static final int INCREMENT_CAP = 2;
    private final List<Difficulty> difficulties = new ArrayList<>(Arrays.asList(new VeryEasy(), new Easy(), new Medium(), new Hard()));


    public WithAutoincrement(IQuizz base) {
        super(base);
        nextDifficultyId = difficulties.indexOf(super.getDifficulty())+1;
    }

    @Override
    public String generateNewQuestion() {
        questionCount++;
        if (questionCount % (INCREMENT_CAP + 1) == 0 && nextDifficultyId < difficulties.size()) {
            Difficulty newDifficulty = difficulties.get(nextDifficultyId);
            super.setDifficulty(newDifficulty);
            nextDifficultyId = nextDifficultyId + 1;
            questionCount = 0;
        }
        return super.generateNewQuestion();
    }

}
