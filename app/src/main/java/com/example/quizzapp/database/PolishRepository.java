package com.example.quizzapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PolishRepository {
    private final PolishDao polishDao;
    private final LiveData<List<PolishWord>> words;


    public PolishRepository(Application application) {
        WordsDatabase database = WordsDatabase.getDatabase(application);
        polishDao = database.polishDao();
        words = polishDao.findAll();
    }


    public LiveData<List<PolishWord>> findAllBooks() {
        return words;
    }

    public void insert(PolishWord word) {
        WordsDatabase.databaseWriteExecutor.execute(() -> polishDao.insert(word));
    }

    public void update(PolishWord word) {
        WordsDatabase.databaseWriteExecutor.execute(() -> polishDao.update(word));
    }

    public void delete(PolishWord word) {
        WordsDatabase.databaseWriteExecutor.execute(() -> polishDao.delete(word));
    }
}
