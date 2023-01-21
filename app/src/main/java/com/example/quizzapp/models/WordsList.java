package com.example.quizzapp.models;

import com.example.quizzapp.database.Word;

import java.util.List;

public class WordsList {
    private final List<Word> words;

    public WordsList(List<Word> words){
        this.words=words;
    }
    public RandomizingIterator<Word> getRandomizingIterator(){
        return new RandomizingIterator<>(words);
    }
    public List<Word> getList(){
        return words;
    }

}
