package com.example.to_dolistapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Notes.class,version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public abstract NotesDao notesDao();
    static synchronized NoteDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database").fallbackToDestructiveMigration()
                    .build();


        }
        return instance;
    }
}
