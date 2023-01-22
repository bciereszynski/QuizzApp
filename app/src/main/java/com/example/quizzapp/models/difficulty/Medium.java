package com.example.quizzapp.models.difficulty;



import androidx.annotation.Nullable;

import com.example.quizzapp.App;
import com.example.quizzapp.R;
import com.example.quizzapp.database.Word;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Medium implements Difficulty {
    public List<String> generateAnswers(List<Word> words, Word correctAnswer) {
        List<String> answers = new LinkedList<>();
        Set<Integer> indexSet = new LinkedHashSet<>();
        int position;
        while (indexSet.size() < 3) {
            position = r.nextInt(words.size());
            if (words.get(position).getContent().equals(correctAnswer.getTranslation()))
                continue;
            indexSet.add(position);
        }
        for (int i : indexSet) {
            answers.add(words.get(i).getContent());
        }
        position = r.nextInt(4);
        answers.add(position, correctAnswer.getTranslation());
        return answers;
    }

    @Override
    public String toString() {
        return App.getResource().getString(R.string.difficultyMedium);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Difficulty)) {
            return false;
        }
        return toString().equals(obj.toString());
    }
}
