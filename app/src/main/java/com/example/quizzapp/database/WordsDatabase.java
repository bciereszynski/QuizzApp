package com.example.quizzapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//UWAGA
//PRZY ZMIANIE BAZY DANYCH KONIECZNIE TRZEBA ZMIENIC NUMER WERSJI
@Database(entities = {PolishWord.class, EnglishWord.class}, version = 1, exportSchema = false)
public abstract class WordsDatabase extends RoomDatabase {
    private static WordsDatabase databaseInstance = null;
    static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public abstract PolishDao polishDao();
    public abstract EnglishDao englishDao();

    static WordsDatabase getDatabase(final Context context){
        if(databaseInstance == null){
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(), WordsDatabase.class, "words_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomDatabaseCallback).build();
        }
        return databaseInstance;
    }

    private static final Callback roomDatabaseCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            databaseWriteExecutor.execute(()->{
                PolishDao polishDao = databaseInstance.polishDao();
                PolishWord word;
                word = new PolishWord("farba", "paint");
                polishDao.insert(word);
                word = new PolishWord("telefon", "phone");
                polishDao.insert(word);
                word = new PolishWord("komputer", "computer");
                polishDao.insert(word);
                word = new PolishWord("ulotka", "flyer");
                polishDao.insert(word);

                EnglishDao englishDao = databaseInstance.englishDao();

                EnglishWord word2;
                word2= new EnglishWord("turtle", "żółw");
                englishDao.insert(word2);
                word2= new EnglishWord("cake", "ciasto");
                englishDao.insert(word2);
                word2= new EnglishWord("tomato", "pomidor");
                englishDao.insert(word2);
                word2= new EnglishWord("cream", "śmietana");
                englishDao.insert(word2);
            });
        }
    };
}


