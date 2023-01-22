package com.example.quizzapp.models.difficulty;


import com.example.quizzapp.database.Word;

import java.util.List;
import java.util.Random;

public interface Difficulty {
    Random r = new Random();
    List<String> generateAnswers(List<Word> words, Word correctAnswer);
}
