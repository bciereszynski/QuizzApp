package com.example.quizzapp.models.quizz;

import com.example.quizzapp.database.Word;
import com.example.quizzapp.models.Observer;
import com.example.quizzapp.models.Question;
import com.example.quizzapp.models.RandomizingIterator;
import com.example.quizzapp.models.WordsList;
import com.example.quizzapp.models.difficulty.Difficulty;
import com.example.quizzapp.models.quizz.mode.Mode;

import java.util.ArrayList;
import java.util.List;

public abstract class Quizz implements IQuizz {
    private Question currentQuestion;
    private List<Word> answerWords;
    private RandomizingIterator<Word> testedWordsIterator;
    private Difficulty difficulty;
    private Mode mode;



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

    protected void setAnswerWords(List<Word> answerWords) {
        this.answerWords = answerWords;
    }
    protected void setTestedWords(List<Word> testedWords){
        this.testedWordsIterator= new WordsList(testedWords).getRandomizingIterator();

    }

    @Override
    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }
    @Override
    public void setMode(Mode mode){
        this.mode = mode;
    }
    @Override
    public void setOwner(Observer owner){
        this.owner = owner;
    }
    @Override
    public void start(){
        attach(owner);
        setWordsLists();
    }


    @Override
    public String generateNewQuestion(){
        if(mode.hasNext() && testedWordsIterator.hasNext() ){
            Word answer = testedWordsIterator.next();
            List<String> possibleAnswers = difficulty.generateAnswers(answerWords, answer);
            this.currentQuestion = new Question(answer, possibleAnswers);
            return "OK";
        }
        else{
            return mode.getEndingText();
        }
    }

    @Override
    public boolean isContinuePossible(){
        return mode.isQuestionCompleted(currentQuestion);
    }


    @Override
    public Question getCurrentQuestion() {
        return currentQuestion;
    }
}
