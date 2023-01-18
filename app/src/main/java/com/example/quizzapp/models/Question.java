package com.example.quizzapp.models;

import com.example.quizzapp.database.Word;

import java.util.List;

public class Question {
    private Word goodAnswer;
    private List<String> possibleAnswers;
    private boolean isAnsweredCorrectly = false;
    private boolean isAnswered = false;

    public Question(Word _goodAnswer, List<String> _possibleAnswers){
        this.goodAnswer = _goodAnswer;
        this.possibleAnswers = _possibleAnswers;
    }
    public Boolean TryAnswer(String answer){
        isAnswered = true;
        if(goodAnswer.getTranslation() == answer){
            isAnsweredCorrectly = true;
            return true;
        }
        isAnsweredCorrectly = false;
        return false;
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
