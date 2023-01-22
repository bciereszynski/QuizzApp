package com.example.quizzapp.models;

import com.example.quizzapp.database.Word;

import java.util.List;

public class Question {
    private final Word goodAnswer;
    private final List<String> possibleAnswers;
    private boolean isAnsweredCorrectly = false;
    private boolean isAnswered = false;

    public Question(Word goodAnswer, List<String> possibleAnswers) {
        this.goodAnswer = goodAnswer;
        this.possibleAnswers = possibleAnswers;
    }

    public void tryAnswer(String answer) {
        isAnswered = true;
        isAnsweredCorrectly = goodAnswer.getTranslation().equals(answer);

    }

    public boolean isAnsweredCorrectly() {
        return isAnsweredCorrectly;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public Word getGoodAnswer() {
        return goodAnswer;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }
}
