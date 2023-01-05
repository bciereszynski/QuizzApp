package com.example.quizzapp.models;

import com.example.quizzapp.database.Word;

import java.util.List;

public class Question {
    private Word goodAnswer;
    private List<Word> possibleAnswers;

    public Question(Word _goodAnswer, List<Word> _possibleAnswers){
        this.goodAnswer = _goodAnswer;
        this.possibleAnswers = _possibleAnswers;
    }
    public Boolean TryAnswer(int id){
        if(goodAnswer.getTranslation() == possibleAnswers.get(id).getContent()){
            return true;
        }
        return false;
    }

    public Word getGoodAnswer() {
        return goodAnswer;
    }

    public List<Word> getPossibleAnswers() {
        return possibleAnswers;
    }
}
