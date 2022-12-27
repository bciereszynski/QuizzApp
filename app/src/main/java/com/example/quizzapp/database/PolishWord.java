package com.example.quizzapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "polish")
public class PolishWord {
    public PolishWord(String content, String translation){
        this.content = content;
        this.translation = translation;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private String translation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }


}
