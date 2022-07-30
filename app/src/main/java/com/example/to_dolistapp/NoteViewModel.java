package com.example.to_dolistapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepo noteRepo;
    private LiveData<List<Notes>> notelist;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepo=new NoteRepo(application);
        notelist=noteRepo.getAllData();
    }
    public void insert(Notes notes){
        noteRepo.insertData(notes);
    }
    public void delete(Notes notes){
        noteRepo.deleteData(notes);
    }
    public void update(Notes notes){
        noteRepo.updateData(notes);
    }
    public LiveData<List<Notes>> getAllNotes(){
        return notelist;
    }
}
