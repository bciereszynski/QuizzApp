package com.example.quizzapp.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PolishWordViewModel extends AndroidViewModel {
    private final PolishRepository polishRepository;
    private final LiveData<List<PolishWord>> words;

    public PolishWordViewModel(@NonNull Application application) {
        super(application);
        polishRepository = new PolishRepository(application);
        words = polishRepository.findAllBooks();
    }

    public LiveData<List<PolishWord>> findAll() {
        return words;
    }

    public void insert(PolishWord word) {
        polishRepository.insert(word);
    }

    public void update(PolishWord word) {
        polishRepository.update(word);
    }

    public void delete(PolishWord word) {
        polishRepository.delete(word);
    }
}
