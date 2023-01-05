package com.example.quizzapp.models;

import com.example.quizzapp.database.EnglishWord;
import com.example.quizzapp.database.Word;

import java.util.List;

public class Quizz {
    private Question currentQuestuion;
    private List<Word> answerWords;
    private RandomizingIterator<Word> testedWordsIterator;
    private Difficulty difficulty;

    public Quizz(List<Word> _testedWords, List<Word> _answerWords){
        this.answerWords = _answerWords;
        this.testedWordsIterator= new WordsList(_testedWords).getRandomizingIterator();
        difficulty = new Medium();
    }

    public void generateNewQuestion(){
        if(testedWordsIterator.hasNext()){
            Word answer = testedWordsIterator.next();
            List<Word> possibleAnswers = difficulty.generateAnswers(answerWords, answer);
            this.currentQuestuion = new Question(answer, possibleAnswers);
        }
        else{
            //TODO :END TEST
        }
    }

    public Question getCurrentQuestuion() {
        return currentQuestuion;
    }
}
