package com.example.quizzapp.models.quizz;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.quizzapp.App;
import com.example.quizzapp.R;
import com.example.quizzapp.database.EnglishWordViewModel;
import com.example.quizzapp.database.PolishWordViewModel;
import com.example.quizzapp.database.Word;

import java.util.LinkedList;
import java.util.List;

public class PL_EN_Quizz extends Quizz {

    public void setWordsLists() {
        PolishWordViewModel polishWordViewModel = new ViewModelProvider((ViewModelStoreOwner) owner).get(PolishWordViewModel.class);
        EnglishWordViewModel englishWordViewModel = new ViewModelProvider((ViewModelStoreOwner) owner).get(EnglishWordViewModel.class);
        List<Word> polish = new LinkedList<>();
        List<Word> english = new LinkedList<>();
        englishWordViewModel.findAll().observe((LifecycleOwner) owner, englishWords -> {
            english.addAll(englishWords);
            polishWordViewModel.findAll().observe((LifecycleOwner) owner, polishWords -> {
                polish.addAll(polishWords);
                setAnswerWords(english);
                setTestedWords(polish);
                callObservers();
            });
        });
    }

    @Override
    public String toString() {
        return App.getResource().getString(R.string.polishToEnglish);
    }

}
