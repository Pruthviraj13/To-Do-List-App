package com.example.to_dolistapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "my_notes")
public class Notes {
    private String disp;
    private String title;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Notes(String disp, String title) {

        this.title = title;
        this.disp = disp;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
