package com.example.quizzapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EnglishRepository {
    private final EnglishDao englishDao;
    private final LiveData<List<EnglishWord>> words;


    public EnglishRepository(Application application) {
        WordsDatabase database = WordsDatabase.getDatabase(application);
        englishDao = database.englishDao();
        words = englishDao.findAll();
    }

    public LiveData<List<EnglishWord>> findAllBooks() {
        return words;
    }

    public void insert(EnglishWord word) {
        WordsDatabase.databaseWriteExecutor.execute(() -> englishDao.insert(word));
    }

    public void update(EnglishWord word) {
        WordsDatabase.databaseWriteExecutor.execute(() -> englishDao.update(word));
    }

    public void delete(EnglishWord word) {
        WordsDatabase.databaseWriteExecutor.execute(() -> englishDao.delete(word));
    }
}
