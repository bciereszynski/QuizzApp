package com.example.quizzapp.models;

import com.example.quizzapp.database.Word;

import java.util.Arrays;
import java.util.List;

public class PL_EN_Quizz extends Quizz{

    public void setWords(List<Word> firstList, List<Word> secondList){
        setAnswerWords(secondList);
        setTestedWords(firstList);
    }

    public PL_EN_Quizz(){
        super();
    }


}
