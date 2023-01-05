package com.example.quizzapp.models;



import com.example.quizzapp.database.Word;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Hard implements Difficulty{
    public List<String> generateAnswers(List<Word> words, Word correctAnswer){
        List<String> answers = new LinkedList<>();
        Random r = new Random();
        Set<Integer> indexSet = new LinkedHashSet<>();
        int position;
        while(indexSet.size() < 5) {
            position = r.nextInt(words.size());
            if(words.get(position).getContent() == correctAnswer.getTranslation())
                continue;
            indexSet.add(position);
        }
        for (int i:indexSet) {
            answers.add(words.get(i).getContent());
        }
        position=r.nextInt(5);
        answers.add(position, correctAnswer.getTranslation());
        return answers;
    }
    public Hard(){

    }

    public Difficulty copy(){
        return new Hard();
    }
}