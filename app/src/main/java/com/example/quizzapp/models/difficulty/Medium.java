package com.example.quizzapp.models.difficulty;



import com.example.quizzapp.database.Word;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Medium implements Difficulty{
    public List<String> generateAnswers(List<Word> words, Word correctAnswer){
        List<String> answers = new LinkedList<>();
        Random r = new Random();
        Set<Integer> indexSet = new LinkedHashSet<>();
        int position;
        while(indexSet.size() < 4) {
            position = r.nextInt(words.size());
            if(words.get(position).getContent() == correctAnswer.getTranslation())
                continue;
            indexSet.add(position);
        }
        for (int i:indexSet) {
            answers.add(words.get(i).getContent());
        }
        position=r.nextInt(4);
        answers.add(position, correctAnswer.getTranslation());
        return answers;
    }
    public Medium(){

    }

    public Difficulty copy(){
        return new Medium();
    }
}