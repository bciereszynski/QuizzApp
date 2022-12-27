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
public interface PolishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PolishWord word);

    @Update
    void update(PolishWord word);

    @Delete
    void delete(PolishWord word);

    @Query("DELETE FROM polish")
    void deleteAll();

    @Query("SELECT * FROM polish ORDER BY content")
    LiveData<List<PolishWord>> findAll();

    @Query("SELECT * FROM polish WHERE content LIKE :content")
    List<PolishWord> findWordByContent(String content);
}
