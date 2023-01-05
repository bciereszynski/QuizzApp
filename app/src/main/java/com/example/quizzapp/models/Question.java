package com.example.quizzapp.models;

import com.example.quizzapp.database.Word;

import java.util.List;

public class Question {
    private Word goodAnswer;
    private List<String> possibleAnswers;

    public Question(Word _goodAnswer, List<String> _possibleAnswers){
        this.goodAnswer = _goodAnswer;
        this.possibleAnswers = _possibleAnswers;
    }
    public Boolean TryAnswer(String answer){
        if(goodAnswer.getTranslation() == answer){
            return true;
        }
        return false;
    }

    public Word getGoodAnswer() {
        return goodAnswer;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }
}
