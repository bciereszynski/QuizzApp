package com.example.quizzapp.models.quizz;

import com.example.quizzapp.database.Word;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.difficulty.Hard;
import com.example.quizzapp.models.difficulty.Medium;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.RandomizingIterator;
import com.example.quizzapp.models.WordsList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Quizz {
    private Question currentQuestion;
    private List<Word> answerWords;
    private RandomizingIterator<Word> testedWordsIterator;
    private Difficulty difficulty;


    //OBSERVED OBJECT
    protected Observer owner;
    protected List<Observer> observers = new ArrayList<Observer>();

    protected void callObservers() {
        for (Observer obs:observers) {
            obs.update();
        }
    }

    protected void attach(Observer obs) {
        observers.add(obs);
    }

    protected void detach(Observer obs) {
        observers.remove(obs);
    }
    ////////////////////////////////

    public abstract void setWordsLists();

    protected void setAnswerWords(List<Word> answerWords) {
        this.answerWords = answerWords;
    }
    protected void setTestedWords(List<Word> testedWords){
        this.testedWordsIterator= new WordsList(testedWords).getRandomizingIterator();

    }

    //private static List<Difficulty> difficulties = Arrays.asList(new Medium(), new Hard());

    public Quizz(Observer owner){
        this.owner = owner;
        attach((Observer) owner);
        difficulty = new Medium();
        setWordsLists();
    }

    public void generateNewQuestion(){
        if(testedWordsIterator.hasNext()){
            Word answer = testedWordsIterator.next();
            List<String> possibleAnswers = difficulty.generateAnswers(answerWords, answer);
            this.currentQuestion = new Question(answer, possibleAnswers);
            callObservers();
        }
        else{
            //TODO :END TEST
        }
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }
}
