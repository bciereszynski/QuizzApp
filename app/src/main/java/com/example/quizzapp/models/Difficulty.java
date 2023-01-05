package com.example.quizzapp.models;



import com.example.quizzapp.database.Word;

import java.util.List;

public interface Difficulty {
    public List<Word> generateAnswers(List<Word> words, Word correctAnswer);
}
