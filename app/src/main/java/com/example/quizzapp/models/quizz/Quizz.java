package com.example.quizzapp.models.quizz;

import com.example.quizzapp.database.Word;
import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.RandomizingIterator;
import com.example.quizzapp.models.WordsList;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.mode.Mode;

import java.util.ArrayList;
import java.util.List;

public abstract class Quizz {
    private Question currentQuestion;
    private List<Word> answerWords;
    private RandomizingIterator<Word> testedWordsIterator;
    private Difficulty difficulty;
    private Mode mode;
    private int questionNr = 0;


    //OBSERVED OBJECT
    protected Observer owner;
    protected List<Observer> observers = new ArrayList<>();

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


    //todo: Bezparametrowo?
    protected Quizz(Observer owner, Difficulty startDifficulty, Mode mode){
        this.owner = owner;
        attach((Observer) owner);
        this.mode = mode;
        this.difficulty = startDifficulty;
        setWordsLists();
    }


    public void generateNewQuestion(){
        questionNr++;
        if(testedWordsIterator.hasNext()){
            Word answer = testedWordsIterator.next();
            List<String> possibleAnswers = difficulty.generateAnswers(answerWords, answer);
            this.currentQuestion = new Question(answer, possibleAnswers);
        }
        else{
            //TODO: END TEST
        }
    }

    public boolean isContinuePossible(){
        return mode.isContinuePossible(currentQuestion);
    }


    public Question getCurrentQuestion() {
        return currentQuestion;
    }
}
