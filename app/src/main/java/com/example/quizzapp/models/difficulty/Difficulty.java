package com.example.quizzapp.models.difficulty;



import com.example.quizzapp.database.Word;

import java.util.List;

public interface Difficulty {
    public List<String> generateAnswers(List<Word> words, Word correctAnswer);
}
