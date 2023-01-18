package com.example.quizzapp.models;

import com.example.quizzapp.database.Word;

import java.util.List;

public class EN_PL_Quizz extends Quizz{

    public void setWords(List<Word> firstList, List<Word> secondList){
        setAnswerWords(firstList);
        setTestedWords(secondList);
    }

    public EN_PL_Quizz(){
        super();
    }


}
