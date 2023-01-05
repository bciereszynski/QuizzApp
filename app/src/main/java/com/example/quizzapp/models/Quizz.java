package com.example.quizzapp.models;

import com.example.quizzapp.database.EnglishWord;
import com.example.quizzapp.database.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quizz {
    private Question currentQuestuion;
    private List<Word> answerWords;
    private RandomizingIterator<Word> testedWordsIterator;
    private Difficulty difficulty;

    private static List<Difficulty> difficulties = Arrays.asList(new Medium(), new Hard());


    public Quizz(List<Word> _testedWords, List<Word> _answerWords){
        this.answerWords = _answerWords;
        this.testedWordsIterator= new WordsList(_testedWords).getRandomizingIterator();
        difficulty = new Medium();
    }

    public Quizz(){
        difficulty = new Medium();
    }

    public void setAnswerWords(List<Word> answerWords) {
        this.answerWords = answerWords;
    }
    public void setTestedWords(List<Word> testedWords){
        this.testedWordsIterator= new WordsList(testedWords).getRandomizingIterator();
    }

    public void generateNewQuestion(){
        if(testedWordsIterator.hasNext()){
            Word answer = testedWordsIterator.next();
            List<String> possibleAnswers = difficulty.generateAnswers(answerWords, answer);
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
