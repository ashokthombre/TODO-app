package com.example.todoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName ="my_notes")
public class Notes {

    private String title;

    private String disc;

@PrimaryKey(autoGenerate = true)
    private int id;



    public Notes(String title, String disc) {
        this.title = title;
        this.disc = disc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
