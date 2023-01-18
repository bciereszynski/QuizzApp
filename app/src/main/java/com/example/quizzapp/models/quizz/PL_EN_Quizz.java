package com.example.quizzapp.models.quizz;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.quizzapp.database.EnglishWordViewModel;
import com.example.quizzapp.database.PolishWordViewModel;
import com.example.quizzapp.database.Word;

import java.util.LinkedList;
import java.util.List;

public class PL_EN_Quizz extends Quizz {

    public void setWordsLists(){
        PolishWordViewModel polishWordViewModel = new ViewModelProvider((ViewModelStoreOwner)owner).get(PolishWordViewModel.class);
        EnglishWordViewModel englishWordViewModel = new ViewModelProvider((ViewModelStoreOwner)owner).get(EnglishWordViewModel.class);
        List<Word> polish = new LinkedList<Word>();
        List<Word> english = new LinkedList<Word>();
        englishWordViewModel.findAll().observe((LifecycleOwner)owner, englishWords -> {
            for (Word w : englishWords) {
                english.add(w);
            }
            polishWordViewModel.findAll().observe((LifecycleOwner)owner, polishWords -> {
                for (Word w : polishWords) {
                    polish.add(w);
                }
                setAnswerWords(english);
                setTestedWords(polish);
                callObservers();
            });

        });

    }

    public PL_EN_Quizz(Observer owner){
        super(owner);
    }


}
