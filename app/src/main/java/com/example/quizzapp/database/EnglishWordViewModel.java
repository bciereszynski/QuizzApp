package com.example.quizzapp.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EnglishWordViewModel extends AndroidViewModel {
    private final EnglishRepository englishRepository;
    private final LiveData<List<EnglishWord>> words;

    public EnglishWordViewModel(@NonNull Application application) {
        super(application);
        englishRepository = new EnglishRepository(application);
        words = englishRepository.findAllBooks();
    }

    public LiveData<List<EnglishWord>> findAll() {
        return words;
    }

    public void insert(EnglishWord word) {
        englishRepository.insert(word);
    }

    public void update(EnglishWord word) {
        englishRepository.update(word);
    }

    public void delete(EnglishWord word) {
        englishRepository.delete(word);
    }
}
