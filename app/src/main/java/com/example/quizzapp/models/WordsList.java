package com.example.quizzapp.models;

import com.example.quizzapp.database.Word;

import java.util.List;

public class WordsList {
    private List<Word> words;

    public WordsList(List<Word> _words){
        this.words=_words;
    }
    public RandomizingIterator<Word> getRandomizingIterator(){
        return new RandomizingIterator<Word>(words);
    }
    public List<Word> getList(){
        return words;
    }

}
