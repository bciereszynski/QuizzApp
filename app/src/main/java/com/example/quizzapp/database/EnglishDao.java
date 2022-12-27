package com.example.quizzapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EnglishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EnglishWord word);

    @Update
    void update(EnglishWord word);

    @Delete
    void delete(EnglishWord word);

    @Query("DELETE FROM english")
    void deleteAll();

    @Query("SELECT * FROM english")
    LiveData<List<EnglishWord>> findAll();

    @Query("SELECT * FROM english WHERE content LIKE :content")
    List<EnglishWord> findWordByContent(String content);
}
